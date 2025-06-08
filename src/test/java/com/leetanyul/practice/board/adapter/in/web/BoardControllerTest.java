package com.leetanyul.practice.board.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leetanyul.practice.account.domain.AccountId;
import com.leetanyul.practice.board.application.port.in.CreateBoardCommand;
import com.leetanyul.practice.board.application.port.in.CreateBoardUseCase;
import com.leetanyul.practice.board.application.port.in.GetBoardUseCase;
import com.leetanyul.practice.board.application.port.in.GetBoardsByCreatorUseCase;
import com.leetanyul.practice.board.domain.Board;
import com.leetanyul.practice.board.domain.BoardId;
import com.leetanyul.practice.auth.domain.JwtTokenProvider;
import com.leetanyul.practice.config.TestJwtConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestJwtConfig.class)
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private GetBoardUseCase getBoardUseCase;

    @MockBean
    private CreateBoardUseCase createBoardUseCase;

    @MockBean
    private GetBoardsByCreatorUseCase getBoardsByCreatorUseCase;

    private String JWT_TOKEN;

    @BeforeEach
    void setup() {
        JWT_TOKEN = "Bearer " + jwtTokenProvider.createToken("leetanyul@example.com", true);
    }

    @Test
    @DisplayName("게시물 단건 조회 성공")
    void getBoard_success() throws Exception {
        Board board = new Board(
                new BoardId(1L),
                "테스트 제목",
                "내용입니다.",
                new AccountId(1L)
        );
        when(getBoardUseCase.getBoard(any(BoardId.class))).thenReturn(board);

        mockMvc.perform(get("/boards/1")
                        .header("Authorization", JWT_TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title").value("테스트 제목"))
                .andExpect(jsonPath("$.data.content").value("내용입니다."));
    }

    @Test
    @DisplayName("게시물 생성 성공")
    void createBoard_success() throws Exception {
        BoardController.CreateBoardRequest request = new BoardController.CreateBoardRequest("새 제목", "새 내용", 100L);

        when(createBoardUseCase.createBoard(any(CreateBoardCommand.class)))
                .thenReturn(new BoardId(1L));

        mockMvc.perform(post("/boards")
                        .header("Authorization", JWT_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("게시물 생성됨 ID: 1"));
    }

    @Test
    @DisplayName("작성자 기준 게시물 목록 조회 성공")
    void getBoardsByCreator_success() throws Exception {
        List<Board> boards = List.of(
                new Board(new BoardId(1L), "제목1", "내용1", new AccountId(100L)),
                new Board(new BoardId(2L), "제목2", "내용2", new AccountId(100L))
        );
        when(getBoardsByCreatorUseCase.getBoardsByCreator(eq(new AccountId(100L))))
                .thenReturn(boards);

        mockMvc.perform(get("/boards/creator-id/100")
                        .header("Authorization", JWT_TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].title").value("제목1"))
                .andExpect(jsonPath("$.data[1].title").value("제목2"));
    }

    @Test
    @DisplayName("인증 없이 접근 시 401")
    void getBoard_unauthorized() throws Exception {
        mockMvc.perform(get("/boards/1")) // No Authorization header
                .andExpect(status().isUnauthorized());
    }
}

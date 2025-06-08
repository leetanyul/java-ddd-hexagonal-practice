package com.leetanyul.practice.board.adapter.in.web;

import com.leetanyul.practice.account.domain.Account;
import com.leetanyul.practice.account.domain.AccountId;
import com.leetanyul.practice.auth.domain.JwtTokenProvider;
import com.leetanyul.practice.board.application.port.in.CreateBoardCommand;
import com.leetanyul.practice.board.application.port.in.CreateBoardUseCase;
import com.leetanyul.practice.board.application.port.in.GetBoardUseCase;
import com.leetanyul.practice.board.application.port.in.GetBoardsByCreatorUseCase;
import com.leetanyul.practice.board.domain.Board;
import com.leetanyul.practice.board.domain.BoardId;
import com.leetanyul.practice.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final GetBoardUseCase getBoardUseCase;
    private final CreateBoardUseCase createBoardUseCase;
    private final GetBoardsByCreatorUseCase getBoardsByCreatorUseCase;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Board>> getBoard(@PathVariable Long id) {
        Board board = getBoardUseCase.getBoard(new BoardId(id));
        return ResponseEntity.ok(ApiResponse.success(board));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Board>>> getAllBoards() {
        List<Board> boards = getBoardUseCase.getBoards();
        return ResponseEntity.ok(ApiResponse.success(boards));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createBoard(
            @RequestBody CreateBoardRequest request,
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        AccountId accountId = jwtTokenProvider.getAccountId(token);

        CreateBoardCommand command = new CreateBoardCommand(
                request.title(),
                request.content(),
                accountId
        );

        BoardId boardId = createBoardUseCase.createBoard(command);
        return ResponseEntity.ok(ApiResponse.success("게시물 생성됨 ID: " + boardId.getValue()));
    }


    @GetMapping("/creator-id/{accountId}")
    public ResponseEntity<ApiResponse<List<BoardResponse>>> getBoardsByCreator(@PathVariable Long accountId) {
        List<BoardResponse> responses = getBoardsByCreatorUseCase.getBoardsByCreator(new AccountId(accountId))
                .stream()
                .map(board -> new BoardResponse(board.getId().getValue(), board.getTitle(), board.getContent()))
                .toList();
        return ResponseEntity.ok(ApiResponse.success(responses));
    }

    public record CreateBoardRequest(String title, String content) {
    }

    public record BoardResponse(Long id, String title, String content) {
    }

    @PreAuthorize("@customPermissionChecker.isAdmin()")
    @GetMapping("/admin-only")
    public ResponseEntity<ApiResponse<String>> adminOnlyApi() {
        return ResponseEntity.ok(ApiResponse.success("관리자 전용 API입니다!"));
    }

    @GetMapping("/error-test")
    public String errorTest() {
        throw new IllegalArgumentException("잘못된 요청입니다.");
    }
}

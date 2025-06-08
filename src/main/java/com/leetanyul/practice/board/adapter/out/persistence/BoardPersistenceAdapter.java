package com.leetanyul.practice.board.adapter.out.persistence;

import com.leetanyul.practice.account.domain.AccountId;
import com.leetanyul.practice.board.application.port.out.CreateBoardPort;
import com.leetanyul.practice.board.application.port.out.LoadBoardPort;
import com.leetanyul.practice.board.application.port.out.LoadBoardsByCreatorPort;
import com.leetanyul.practice.board.domain.Board;
import com.leetanyul.practice.board.domain.BoardId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardPersistenceAdapter implements CreateBoardPort, LoadBoardPort, LoadBoardsByCreatorPort {

    private final BoardRepository boardRepository;

    @Override
    public Board loadBoard(BoardId boardId) {
        BoardJpaEntity entity = boardRepository.findById(boardId.getValue())
                .orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));
        return new Board(
                new BoardId(entity.getId()),
                entity.getTitle(),
                entity.getContent(),
                new AccountId(entity.getCreatorId()) // 추가
        );
    }

    @Override
    public List<Board> loadAllBoards() {
        return boardRepository.findAll().stream()
                .map(entity -> new Board(
                        new BoardId(entity.getId()),
                        entity.getTitle(),
                        entity.getContent(),
                        new AccountId(entity.getCreatorId()) // 추가
                ))
                .toList();
    }

    @Override
    public Board save(Board board) {
        BoardJpaEntity entity = new BoardJpaEntity();
        entity.setTitle(board.getTitle());
        entity.setContent(board.getContent());
        entity.setCreatorId(board.getCreatorId().getValue());

        BoardJpaEntity saved = boardRepository.save(entity);
        return new Board(
                new BoardId(saved.getId()),
                saved.getTitle(),
                saved.getContent(),
                new AccountId(saved.getCreatorId())
        );
    }

    @Override
    public List<Board> loadBoardsByCreator(AccountId creatorId) {
        return boardRepository.findByCreatorId(creatorId.getValue())
                .stream()
                .map(entity -> new Board(
                        new BoardId(entity.getId()), // 여기 고침
                        entity.getTitle(),
                        entity.getContent(),
                        new AccountId(entity.getCreatorId())
                ))
                .toList();
    }

}

package com.leetanyul.practice.board.application.service;

import com.leetanyul.practice.board.application.port.in.CreateBoardCommand;
import com.leetanyul.practice.board.application.port.in.CreateBoardUseCase;
import com.leetanyul.practice.board.application.port.out.CreateBoardPort;
import com.leetanyul.practice.board.domain.Board;
import com.leetanyul.practice.board.domain.BoardId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateBoardService implements CreateBoardUseCase {

    private final CreateBoardPort createBoardPort;

    @Override
    public BoardId createBoard(CreateBoardCommand command) {
        Board board = new Board(
                null,
                command.getTitle(),
                command.getContent(),
                command.getCreatorId()
        );
        return createBoardPort.save(board).getId();
    }
}

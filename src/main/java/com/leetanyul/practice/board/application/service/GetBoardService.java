package com.leetanyul.practice.board.application.service;

import com.leetanyul.practice.board.application.port.in.GetBoardUseCase;
import com.leetanyul.practice.board.application.port.out.LoadBoardPort;
import com.leetanyul.practice.board.domain.Board;
import com.leetanyul.practice.board.domain.BoardId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetBoardService implements GetBoardUseCase {

    private final LoadBoardPort loadBoardPort;

    public GetBoardService(LoadBoardPort loadBoardPort) {
        this.loadBoardPort = loadBoardPort;
    }

    @Override
    public Board getBoard(BoardId boardId) {
        return loadBoardPort.loadBoard(boardId);
    }

    @Override
    public List<Board> getBoards() {
        return loadBoardPort.loadAllBoards();
    }
}

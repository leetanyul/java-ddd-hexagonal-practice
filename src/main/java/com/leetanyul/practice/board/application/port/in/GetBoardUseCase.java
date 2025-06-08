package com.leetanyul.practice.board.application.port.in;

import com.leetanyul.practice.board.domain.Board;
import com.leetanyul.practice.board.domain.BoardId;

import java.util.List;

public interface GetBoardUseCase {
    Board getBoard(BoardId boardId);

    List<Board> getBoards();
}

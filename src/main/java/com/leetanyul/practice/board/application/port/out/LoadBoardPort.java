package com.leetanyul.practice.board.application.port.out;

import com.leetanyul.practice.board.domain.Board;
import com.leetanyul.practice.board.domain.BoardId;

import java.util.List;

public interface LoadBoardPort {
    Board loadBoard(BoardId boardId);

    List<Board> loadAllBoards();

    Board save(Board board);
}

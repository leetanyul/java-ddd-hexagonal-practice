package com.leetanyul.practice.board.application.port.out;

import com.leetanyul.practice.board.domain.Board;

public interface CreateBoardPort {
    Board save(Board board);
}

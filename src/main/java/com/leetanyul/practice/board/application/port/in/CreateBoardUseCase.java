package com.leetanyul.practice.board.application.port.in;

import com.leetanyul.practice.board.domain.BoardId;

public interface CreateBoardUseCase {
    BoardId createBoard(CreateBoardCommand command);
}

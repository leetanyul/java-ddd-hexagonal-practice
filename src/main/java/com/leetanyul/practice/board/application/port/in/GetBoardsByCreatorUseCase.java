package com.leetanyul.practice.board.application.port.in;

import com.leetanyul.practice.account.domain.AccountId;
import com.leetanyul.practice.board.domain.Board;

import java.util.List;

public interface GetBoardsByCreatorUseCase {
    List<Board> getBoardsByCreator(AccountId creatorId);
}

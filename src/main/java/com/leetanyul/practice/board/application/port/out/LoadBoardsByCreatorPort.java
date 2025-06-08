package com.leetanyul.practice.board.application.port.out;

import com.leetanyul.practice.account.domain.AccountId;
import com.leetanyul.practice.board.domain.Board;

import java.util.List;

public interface LoadBoardsByCreatorPort {
    List<Board> loadBoardsByCreator(AccountId creatorId);
}

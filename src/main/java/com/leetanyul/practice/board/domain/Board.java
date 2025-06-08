package com.leetanyul.practice.board.domain;

import com.leetanyul.practice.account.domain.AccountId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Board {
    private final BoardId id;
    private final String title;
    private final String content;
    private final AccountId creatorId;
}

package com.leetanyul.practice.board.application.port.in;

import com.leetanyul.practice.account.domain.AccountId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateBoardCommand {
    private final String title;
    private final String content;
    private final AccountId creatorId;
}

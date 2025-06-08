package com.leetanyul.practice.account.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Account {

    private final AccountId id;
    private final String name;
    private final String email;
    private final boolean isAdmin;
}

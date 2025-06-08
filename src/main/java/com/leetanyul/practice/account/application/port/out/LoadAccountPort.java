package com.leetanyul.practice.account.application.port.out;

import com.leetanyul.practice.account.domain.Account;
import com.leetanyul.practice.account.domain.AccountId;

import java.util.Optional;

public interface LoadAccountPort {
    Account loadAccount(AccountId accountId);

    Optional<Account> findByEmail(String email);
}

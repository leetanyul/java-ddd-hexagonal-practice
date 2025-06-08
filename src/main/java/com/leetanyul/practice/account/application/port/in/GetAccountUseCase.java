package com.leetanyul.practice.account.application.port.in;

import com.leetanyul.practice.account.domain.Account;
import com.leetanyul.practice.account.domain.AccountId;

public interface GetAccountUseCase {
    Account getAccount(AccountId accountId);
}

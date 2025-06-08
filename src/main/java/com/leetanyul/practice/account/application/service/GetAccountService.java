package com.leetanyul.practice.account.application.service;

import com.leetanyul.practice.account.application.port.in.GetAccountUseCase;
import com.leetanyul.practice.account.application.port.out.LoadAccountPort;
import com.leetanyul.practice.account.domain.Account;
import com.leetanyul.practice.account.domain.AccountId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAccountService implements GetAccountUseCase {

    private final LoadAccountPort loadAccountPort;

    @Override
    public Account getAccount(AccountId accountId) {
        return loadAccountPort.loadAccount(accountId);
    }
}

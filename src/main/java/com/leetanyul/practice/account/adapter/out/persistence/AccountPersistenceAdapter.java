package com.leetanyul.practice.account.adapter.out.persistence;

import com.leetanyul.practice.account.application.port.out.LoadAccountPort;
import com.leetanyul.practice.account.domain.Account;
import com.leetanyul.practice.account.domain.AccountId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements LoadAccountPort {

    private final AccountRepository accountRepository;

    @Override
    public Account loadAccount(AccountId accountId) {
        AccountJpaEntity entity = accountRepository.findById(accountId.getValue())
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + accountId));

        return new Account(
                new AccountId(entity.getId()),
                entity.getName(),
                entity.getEmail(),
                entity.isAdmin()
        );
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email)
                .map(entity -> new Account(
                        new AccountId(entity.getId()),
                        entity.getName(),
                        entity.getEmail(),
                        entity.isAdmin()
                ));
    }
}

package com.leetanyul.practice.account.adapter.in.web;

import com.leetanyul.practice.account.application.port.in.GetAccountUseCase;
import com.leetanyul.practice.account.domain.Account;
import com.leetanyul.practice.account.domain.AccountId;
import com.leetanyul.practice.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final GetAccountUseCase getAccountUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountResponse>> getAccount(@PathVariable("id") Long id) {
        Account account = getAccountUseCase.getAccount(new AccountId(id));
        AccountResponse response = new AccountResponse(
                account.getId().getValue(),
                account.getName(),
                account.getEmail()
        );
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    public record AccountResponse(Long id, String name, String email) {
    }
}

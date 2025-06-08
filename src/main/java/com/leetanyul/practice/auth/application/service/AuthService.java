package com.leetanyul.practice.auth.application.service;

import com.leetanyul.practice.account.domain.Account;
import com.leetanyul.practice.account.application.port.out.LoadAccountPort;
import com.leetanyul.practice.auth.domain.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final LoadAccountPort loadAccountPort; // 이메일로 계정 조회를 위한 포트

    public String generateToken(String email) {
        Account account = loadAccountPort.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("계정을 찾을 수 없습니다: " + email));

        return jwtTokenProvider.createToken(email, account.isAdmin());
    }
}

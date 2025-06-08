package com.leetanyul.practice.config;

import com.leetanyul.practice.auth.domain.JwtTokenProvider;
import com.leetanyul.practice.common.mock.MockJwtTokenProvider;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestJwtConfig {

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new MockJwtTokenProvider();
    }
}

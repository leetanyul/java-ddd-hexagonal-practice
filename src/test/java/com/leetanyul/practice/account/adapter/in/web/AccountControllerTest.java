// src/test/java/com/leetanyul/practice/account/adapter/in/web/AccountControllerTest.java
package com.leetanyul.practice.account.adapter.in.web;

import com.leetanyul.practice.account.application.port.in.GetAccountUseCase;
import com.leetanyul.practice.account.domain.Account;
import com.leetanyul.practice.account.domain.AccountId;
import com.leetanyul.practice.auth.domain.JwtTokenProvider;
import com.leetanyul.practice.common.response.ApiResponse;
import com.leetanyul.practice.config.TestJwtConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Import(TestJwtConfig.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private GetAccountUseCase getAccountUseCase;

    private String JWT_TOKEN;

    @BeforeEach
    void setup() {
        JWT_TOKEN = "Bearer " + jwtTokenProvider.createToken(new AccountId(1L),"leetanyul@example.com", true);
    }

    @Test
    @DisplayName("계정 단건 조회 성공")
    void getAccountSuccess() throws Exception {
        // given
        Account mockAccount = new Account(new AccountId(1L), "홍길동", "hong@example.com", true);
        Mockito.when(getAccountUseCase.getAccount(any(AccountId.class))).thenReturn(mockAccount);

        // when & then
        mockMvc.perform(get("/api/accounts/1")
                        .header("Authorization", JWT_TOKEN))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("홍길동"))
                .andExpect(jsonPath("$.data.email").value("hong@example.com"));
    }
}

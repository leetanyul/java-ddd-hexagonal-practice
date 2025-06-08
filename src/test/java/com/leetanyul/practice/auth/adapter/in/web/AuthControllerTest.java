package com.leetanyul.practice.auth.adapter.in.web;

import com.leetanyul.practice.auth.application.service.AuthService;
import com.leetanyul.practice.config.TestJwtConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestJwtConfig.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Test
    @DisplayName("토큰 발급 성공 테스트")
    void issueToken_success() throws Exception {
        String email = "test@example.com";
        String expectedToken = "mocked-jwt-token";

        when(authService.generateToken(email)).thenReturn(expectedToken);

        mockMvc.perform(get("/auth/token")
                        .param("email", email))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").value(expectedToken));

    }
}

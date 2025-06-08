package com.leetanyul.practice.auth.adapter.in.web;

import com.leetanyul.practice.auth.application.service.AuthService;
import com.leetanyul.practice.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/token")
    public ResponseEntity<ApiResponse<String>> issueToken(@RequestParam String email) {
        String token = authService.generateToken(email);
        return ResponseEntity.ok(ApiResponse.success(token));
    }
}

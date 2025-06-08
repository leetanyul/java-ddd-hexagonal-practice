package com.leetanyul.practice.common.mock;

import com.leetanyul.practice.auth.domain.JwtTokenProvider;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class MockJwtTokenProvider extends JwtTokenProvider {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Override
    public String createToken(String email, boolean isAdmin) {
        return Jwts.builder()
                .setSubject(email)
                .claim("isAdmin", isAdmin)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(secretKey)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token)
                .getBody().getSubject();
    }

    @Override
    public boolean isAdmin(String token) {
        return Boolean.TRUE.equals(
                Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token)
                        .getBody().get("isAdmin", Boolean.class));
    }
}

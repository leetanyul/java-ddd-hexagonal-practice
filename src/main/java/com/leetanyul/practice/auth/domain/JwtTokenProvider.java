package com.leetanyul.practice.auth.domain;

import com.leetanyul.practice.account.domain.AccountId;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secretKey = "leetanyul-java-ddd-hexagonal-bbs-practice-secret-key-!@#SFAADSG%$#TGASDfgadsf341!";
    private final long validityInMs = 3600000; // 1 hour

    private final Key key;

    public JwtTokenProvider() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String createToken(AccountId accountId, String email, boolean isAdmin) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("isAdmin", isAdmin);
        claims.put("accountId", accountId.getValue());

        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isAdmin(String token) {
        return Boolean.TRUE.equals(getClaims(token).get("isAdmin", Boolean.class));
    }

    public AccountId getAccountId(String token) {
        Long id = getClaims(token).get("accountId", Long.class);
        return new AccountId(id);
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

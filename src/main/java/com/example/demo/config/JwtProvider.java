package com.example.demo.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {

    @Value("${app.jwtSecret:SecretKeyMustBeLongEnoughForHS512AlgorithmVerification}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs:86400000}")
    private int jwtExpirationMs;

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email, Long userId, Set<String> roles) {
        return Jwts.builder()
                .subject(email)
                .claim("userId", userId)
                .claim("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(getKey(), Jwts.SIG.HS512) // Updated for 0.12.x compatibility
                .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getKey()) // 'verifyWith' is preferred in 0.12.x over setSigningKey
                .build()              // <--- THIS IS THE MISSING FIX
                .parseSignedClaims(token) // 'parseSignedClaims' is preferred in 0.12.x
                .getPayload()
                .getSubject();
    }
    
    public Long getUserId(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(getKey())
                    .build()          // <--- THIS IS THE MISSING FIX
                    .parseSignedClaims(token)
                    .getPayload();
            
            return claims.get("userId", Long.class);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser()
                    .verifyWith(getKey())
                    .build()          // <--- THIS IS THE MISSING FIX
                    .parseSignedClaims(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
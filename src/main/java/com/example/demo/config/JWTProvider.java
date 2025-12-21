package com.example.demo.config;

import org.springframework.stereotype.Component;

@Component
public class JWTProvider {

    public String generateToken(String username) {
        return "jwt-token-" + username;
    }
}

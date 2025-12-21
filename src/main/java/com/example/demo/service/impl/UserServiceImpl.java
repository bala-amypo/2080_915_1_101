package com.example.demo.service.impl;

import com.example.demo.security.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    private final JWTProvider jwtProvider;

    @Autowired
    public UserServiceImpl(JWTProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    public String createTokenForUser(String username) {
        return jwtProvider.generateToken(username);
    }

    public boolean validateUserToken(String token) {
        return jwtProvider.validateToken(token);
    }

    public String getUsernameFromToken(String token) {
        return jwtProvider.getUsernameFromToken(token);
    }
}

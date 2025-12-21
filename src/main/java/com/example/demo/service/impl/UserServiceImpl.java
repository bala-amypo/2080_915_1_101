package com.example.demo.service.impl;

import com.example.demo.config.JWTProvider;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final JWTProvider jwtProvider;

    public UserServiceImpl(JWTProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public String login(String username, String password) {
        return jwtProvider.generateToken(username);
    }
}

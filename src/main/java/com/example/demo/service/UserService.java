package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.model.User;

public interface UserService {
    /**
     * Registers a new user, encodes the password, and assigns roles[cite: 215].
     */
    User register(UserRegisterDto dto);

    /**
     * Authenticates a user and returns a JWT token[cite: 217].
     */
    AuthResponse login(AuthRequest request);

    /**
     * Retrieves user details by their unique email address[cite: 219].
     */
    User getByEmail(String email);
}
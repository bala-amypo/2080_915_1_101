package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.User;

public interface UserService {
    User register(UserRegisterDto dto); // [cite: 210]
    AuthResponse login(AuthRequest request); // [cite: 211]
    User getByEmail(String email); // [cite: 212]
}
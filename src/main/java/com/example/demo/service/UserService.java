package com.example.demo.service;

import com.example.demo.dto.*;

public interface UserService {
    AuthResponse login(AuthRequest request);
    void register(UserRegisterDto dto);
}

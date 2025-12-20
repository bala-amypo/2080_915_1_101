package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    // Assume a JwtProvider is available in the security package [cite: 6]

    @Override
    public User register(UserRegisterDto dto) {
        if (dto.getName() == null || dto.getEmail() == null || dto.getPassword() == null) {
            throw new IllegalArgumentException("Required fields missing"); // [cite: 81, 214]
        }
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use"); // [cite: 214]
        }

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword())) // [cite: 215]
                .createdAt(LocalDateTime.now()) // [cite: 215]
                .build();
        
        return userRepository.save(user);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found")); // [cite: 218]

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password"); // [cite: 218]
        }

        // Logic to generate token and return AuthResponse [cite: 217]
        return AuthResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .token("generated-jwt-token")
                .build();
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found")); // [cite: 219]
    }
}
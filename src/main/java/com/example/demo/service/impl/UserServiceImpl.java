package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtProvider;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public User register(UserRegisterDto dto) {
        if (dto.getEmail() == null || dto.getPassword().isEmpty()) throw new IllegalArgumentException("Invalid data"); // [cite: 214]
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword())) // [cite: 215]
                .roles(dto.getRoles() == null ? Set.of(Role.ROLE_USER) : dto.getRoles().stream().map(Role::valueOf).collect(Collectors.toSet()))
                .createdAt(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found")); // [cite: 218]
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) throw new IllegalArgumentException("Bad credentials");
        
        String token = jwtProvider.generateToken(user.getEmail(), user.getRoles().stream().map(Enum::name).collect(Collectors.toList()), user.getId()); // [cite: 217]
        return AuthResponse.builder().token(token).userId(user.getId()).email(user.getEmail())
                .roles(user.getRoles().stream().map(Enum::name).collect(Collectors.toSet())).build();
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found")); // [cite: 219]
    }
}
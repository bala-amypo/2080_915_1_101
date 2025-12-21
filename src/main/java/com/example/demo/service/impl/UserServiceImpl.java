package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtProvider;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final JwtProvider jwt;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository repo, JwtProvider jwt) {
        this.repo = repo;
        this.jwt = jwt;
    }

    @Override
    public void register(UserRegisterDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());
        repo.save(user);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        User user = repo.findByUsername(request.getUsername()).orElseThrow();
        String token = jwt.generateToken(user.getUsername());

        return new AuthResponse(
                token,
                user.getId(),
                user.getUsername(),
                Set.of(user.getRole().name())
        );
    }
}

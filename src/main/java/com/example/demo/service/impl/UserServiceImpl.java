package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.config.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    // ================= REGISTER =================
    @Override
    public User register(UserRegisterDto dto) {

        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        userRepository.findByEmail(dto.getEmail())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("Email already exists");
                });

        Set<Role> roles;
        if (dto.getRoles() == null || dto.getRoles().isEmpty()) {
            roles = Set.of(Role.ROLE_USER);
        } else {
            roles = dto.getRoles().stream()
                    .map(r -> Role.valueOf("ROLE_" + r.toUpperCase()))
                    .collect(Collectors.toSet());
        }

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(roles)
                .createdAt(LocalDateTime.now())
                .build();

        return userRepository.save(user);
    }

    // ================= LOGIN =================
    @Override
    public AuthResponse login(AuthRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        Set<String> roleNames = user.getRoles()
                .stream()
                .map(Enum::name)
                .collect(Collectors.toSet());

        String token = jwtProvider.generateToken(
                user.getEmail(),
                user.getId(),
                roleNames
        );

        return AuthResponse.builder()
                .token(token)
                .userId(user.getId())
                .email(user.getEmail())
                .roles(roleNames)
                .build();
    }

    // ================= GET BY EMAIL =================
    @Override
    public User getByEmail(String email) {

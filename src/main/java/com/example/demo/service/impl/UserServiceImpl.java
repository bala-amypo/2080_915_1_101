package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(UserRegisterDto dto) {

        if (dto.getName() == null || dto.getName().isBlank()
                || dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new IllegalArgumentException("Invalid user details");
        }

        userRepository.findByEmail(dto.getEmail())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("Email already exists");
                });

        Set<Role> roles = (dto.getRoles() == null || dto.getRoles().isEmpty())
                ? Set.of(Role.ROLE_USER)
                : dto.getRoles().stream()
                        .map(Role::valueOf)
                        .collect(Collectors.toSet());

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                // ❗ Plain password (NO ENCODER)
                .password(dto.getPassword())
                .roles(roles)
                .createdAt(LocalDateTime.now())
                .build();

        return userRepository.save(user);
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // ❗ Plain password comparison
        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return new AuthResponse(
                "LOGIN_SUCCESS",
                user.getId(),
                user.getEmail(),
                user.getRoles().stream()
                        .map(Enum::name)
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}

package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public User register(UserRegisterDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .createdAt(LocalDateTime.now())
                .build();

        Set<Role> roles = new HashSet<>();
        if (dto.getRoles() != null) {
            dto.getRoles().forEach(r -> {
                try {
                    roles.add(Role.valueOf(r));
                } catch (Exception e) {
                    roles.add(Role.ROLE_USER);
                }
            });
        } else {
            roles.add(Role.ROLE_USER);
        }
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String token = jwtProvider.generateToken(user.getEmail(), user.getId(),
                user.getRoles().stream().map(Enum::name).collect(Collectors.toList()));

        return AuthResponse.builder()
                .token(token)
                .userId(user.getId())
                .email(user.getEmail())
                .roles(user.getRoles().stream().map(Enum::name).collect(Collectors.toSet()))
                .build();
    }
    
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
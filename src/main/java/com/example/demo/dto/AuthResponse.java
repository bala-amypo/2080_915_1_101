package com.example.demo.dto;

import lombok.*;

@Data 
@Builder // Fixes AuthResponse.builder() in UserServiceImpl
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private Long userId;
    private String email;
    private java.util.Set<String> roles;
}
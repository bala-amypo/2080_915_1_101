package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Set;

@Data
@Builder
public class AuthResponse {
    private String token;     // JWT token [cite: 92]
    private Long userId;      // ID of authenticated user [cite: 93]
    private String email;     // User email [cite: 94]
    private Set<String> roles; // Granted roles (e.g., ROLE_ADMIN) [cite: 95]
}
package com.example.demo.dto;

import lombok.*;
import java.util.Set;

@Data @Builder @NoArgsConstructor @AllArgsConstructor // [cite: 90]
public class AuthResponse {
    private String token; // [cite: 92]
    private Long userId; // [cite: 93]
    private String email; // [cite: 94]
    private Set<String> roles; // [cite: 95]
}
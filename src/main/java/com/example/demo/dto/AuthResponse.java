package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private Long userId;
    private String email;
    private Set<String> roles;
}

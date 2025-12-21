package com.example.demo.dto;

import java.util.Set;

public class AuthResponse {

    private String token;
    private Long userId;
    private String username;
    private Set<String> roles;

    public AuthResponse(String token, Long userId,
                        String username, Set<String> roles) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.roles = roles;
    }

    public String getToken() { return token; }
    public Long getUserId() { return userId; }
    public String getUsername() { return username; }
    public Set<String> getRoles() { return roles; }
}

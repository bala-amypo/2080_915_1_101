package com.example.demo.dto;

import lombok.Data;

@Data // [cite: 85]
public class AuthRequest {
    private String email; // [cite: 87]
    private String password; // [cite: 88]
}
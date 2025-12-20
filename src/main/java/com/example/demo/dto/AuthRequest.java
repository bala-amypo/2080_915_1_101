package com.example.demo.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;    // Email used for login [cite: 87]
    private String password; // Raw password provided by user [cite: 88]
}
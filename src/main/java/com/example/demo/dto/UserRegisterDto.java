package com.example.demo.dto;

import lombok.Data;
import java.util.Set;

@Data
public class UserRegisterDto {
    private String name;     // User full name [cite: 100]
    private String email;    // Registration email [cite: 101]
    private String password; // Raw password to be encoded [cite: 102]
    private Set<String> roles; // Optional roles [cite: 103]
}
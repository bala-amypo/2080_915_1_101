package com.example.demo.dto;

import lombok.Data;
import java.util.Set;

@Data // [cite: 98]
public class UserRegisterDto {
    private String name; // [cite: 100]
    private String email; // [cite: 101]
    private String password; // [cite: 102]
    private Set<String> roles; // [cite: 103]
}
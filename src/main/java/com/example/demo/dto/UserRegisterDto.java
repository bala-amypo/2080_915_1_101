package com.example.demo.dto;

import lombok.*;

@Data // Fixes getName, getEmail, getPassword, getRoles in UserServiceImpl
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
    private String name;
    private String email;
    private String password;
    private java.util.Set<String> roles;
}
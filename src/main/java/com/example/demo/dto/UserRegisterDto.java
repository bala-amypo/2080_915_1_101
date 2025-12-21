package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // This generates getUsername(), getEmail(), getPassword()
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
    private String username;
    private String email;
    private String password;
}
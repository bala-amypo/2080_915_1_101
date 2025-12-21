package com.example.demo.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserRegisterDto {
    private String username;
    private String email;
    private String password;
}
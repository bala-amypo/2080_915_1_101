package com.example.demo.security;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public abstract class CustomUserDetailsService implements UserDetailsService {
    public void loadUserByUsername() {} 
}
package com.example.demo.exception;

// Simple runtime exception for missing resources [cite: 132]
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
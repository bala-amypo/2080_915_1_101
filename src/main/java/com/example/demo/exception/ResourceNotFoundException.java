package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException { // [cite: 132]
    public ResourceNotFoundException(String message) {
        super(message); // [cite: 133, 137]
    }
}
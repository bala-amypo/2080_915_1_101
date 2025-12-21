package com.example.demo.dto;

public class PredictionResponse {

    private String message;

    public PredictionResponse(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }
}

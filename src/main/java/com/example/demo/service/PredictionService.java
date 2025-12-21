package com.example.demo.service;

import com.example.demo.dto.PredictionResponse;

public interface PredictionService {

    PredictionResponse predict(Long stockRecordId);
}

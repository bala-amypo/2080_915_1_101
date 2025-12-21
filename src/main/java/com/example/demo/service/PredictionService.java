package com.example.demo.service;

import com.example.demo.dto.PredictionResponse;
import com.example.demo.model.PredictionRule;

import java.util.List;

public interface PredictionService {

    PredictionResponse predict(Long stockRecordId);

    PredictionRule createRule(PredictionRule rule);

    List<PredictionRule> getAllRules();
}

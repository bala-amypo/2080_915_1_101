package com.example.demo.service;

import com.example.demo.model.PredictionRule;

import java.time.LocalDate;
import java.util.List;

public interface PredictionService {

    /* ===== REQUIRED BY TESTS & CONTROLLER ===== */
    LocalDate predictRestockDate(String stockRecordId);

    /* ===== RULE MANAGEMENT ===== */
    PredictionRule createRule(PredictionRule rule);

    List<PredictionRule> getAllRules();
}

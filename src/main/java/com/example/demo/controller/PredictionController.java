package com.example.demo.controller;

import com.example.demo.model.PredictionRule;
import com.example.demo.service.PredictionService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/predict")
public class PredictionController {

    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    // PREDICT RESTOCK DATE
    @GetMapping("/restock-date/{stockRecordId}")
    public LocalDate predictRestockDate(@PathVariable Long stockRecordId) {
        return predictionService.predictRestockDate(stockRecordId);
    }

    // CREATE RULE
    @PostMapping("/rules")
    public PredictionRule createRule(@RequestBody PredictionRule rule) {
        return predictionService.createRule(rule);
    }

    // GET ALL RULES
    @GetMapping("/rules")
    public List<PredictionRule> getAllRules() {
        return predictionService.getAllRules();
    }
}

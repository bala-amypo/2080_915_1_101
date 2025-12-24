package com.example.demo.controller;

import com.example.demo.model.PredictionRule;
import com.example.demo.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/predict")
public class PredictionController {
    @Autowired
    private PredictionService predictionService;

    @PostMapping("/rules")
    public ResponseEntity<PredictionRule> createRule(@RequestBody PredictionRule rule) {
        return ResponseEntity.ok(predictionService.createRule(rule));
    }

    @GetMapping("/rules")
    public ResponseEntity<List<PredictionRule>> getRules() {
        return ResponseEntity.ok(predictionService.getAllRules());
    }

    @GetMapping("/restock-date/{stockRecordId}")
    public ResponseEntity<String> predictDate(@PathVariable Long stockRecordId) {
        LocalDate date = predictionService.predictRestockDate(stockRecordId);
        return ResponseEntity.ok(date.toString());
    }
}
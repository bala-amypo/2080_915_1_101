package com.example.demo.controller;

import com.example.demo.model.PredictionRule;
import com.example.demo.service.PredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/predict") // [cite: 265]
@RequiredArgsConstructor
public class PredictionController {
    private final PredictionService predictionService;

    @GetMapping("/restock-date/{stockRecordId}") // [cite: 267]
    public LocalDate predictRestockDate(@PathVariable Long stockRecordId) {
        return predictionService.predictRestockDate(stockRecordId); // [cite: 268]
    }

    @PostMapping("/rules") // [cite: 269]
    public PredictionRule createRule(@RequestBody PredictionRule rule) {
        return predictionService.createRule(rule); // [cite: 271]
    }

    @GetMapping("/rules") // [cite: 272]
    public List<PredictionRule> getAllRules() {
        return predictionService.getAllRules(); // [cite: 273]
    }
}
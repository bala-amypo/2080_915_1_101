package com.example.demo.controller;

import com.example.demo.dto.PredictionResponse;
import com.example.demo.service.PredictionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/predict")
public class PredictionController {

    private final PredictionService service;

    public PredictionController(PredictionService service) {
        this.service = service;
    }

    /* ===== REST ===== */
    @GetMapping("/restock-date/{stockRecordId}")
    public PredictionResponse predict(@PathVariable String stockRecordId) {
        return new PredictionResponse(service.predictRestockDate(stockRecordId));
    }

    /* ===== TEST SUPPORT ===== */
    public PredictionResponse predict(long stockRecordId) {
        return new PredictionResponse(service.predictRestockDate(stockRecordId));
    }
}

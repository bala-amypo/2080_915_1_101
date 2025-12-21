package com.example.demo.controller;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/consumption")
@RequiredArgsConstructor
public class ConsumptionLogController {
    private final ConsumptionLogService consumptionLogService;

    @PostMapping("/{stockRecordId}")
    public ResponseEntity<ConsumptionLog> logConsumption(@PathVariable Long stockRecordId,
                                                         @RequestBody ConsumptionLog log) {
        return ResponseEntity.ok(consumptionLogService.logConsumption(stockRecordId, log));
    }

    @GetMapping("/record/{stockRecordId}")
    public ResponseEntity<List<ConsumptionLog>> getLogsByStockRecord(@PathVariable Long stockRecordId) {
        return ResponseEntity.ok(consumptionLogService.getLogsByStockRecord(stockRecordId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumptionLog> getLog(@PathVariable Long id) {
        return ResponseEntity.ok(consumptionLogService.getLog(id));
    }
}
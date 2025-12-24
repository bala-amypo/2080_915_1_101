package com.example.demo.controller;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/consumption")
public class ConsumptionLogController {
    @Autowired
    private ConsumptionLogService service;

    @PostMapping("/{stockRecordId}")
    public ResponseEntity<ConsumptionLog> logConsumption(@PathVariable Long stockRecordId,
                                                         @RequestBody ConsumptionLog log) {
        return ResponseEntity.ok(service.logConsumption(stockRecordId, log));
    }

    @GetMapping("/record/{stockRecordId}")
    public ResponseEntity<List<ConsumptionLog>> getLogs(@PathVariable Long stockRecordId) {
        return ResponseEntity.ok(service.getLogsByStockRecord(stockRecordId));
    }
}
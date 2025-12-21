package com.example.demo.controller;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumption")
public class ConsumptionLogController {

    private final ConsumptionLogService consumptionLogService;

    public ConsumptionLogController(ConsumptionLogService consumptionLogService) {
        this.consumptionLogService = consumptionLogService;
    }

    @PostMapping
    public ConsumptionLog createLog(@RequestBody ConsumptionLog log) {
        return consumptionLogService.saveLog(log);
    }

    @GetMapping("/stock/{stockId}")
    public List<ConsumptionLog> getLogs(@PathVariable Long stockId) {
        return consumptionLogService.getLogsByStockRecordId(stockId);
    }
}

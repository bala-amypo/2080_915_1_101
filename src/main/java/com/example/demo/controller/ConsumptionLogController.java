package com.example.demo.controller;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumption")
public class ConsumptionLogController {

    private final ConsumptionLogService service;

    public ConsumptionLogController(ConsumptionLogService service) {
        this.service = service;
    }

    /* ===== REST ===== */
    @GetMapping("/{id}")
    public ConsumptionLog getLog(@PathVariable String id) {
        return service.getLog(id);
    }

    /* ===== TEST SUPPORT ===== */
    public ConsumptionLog getLog(long id) {
        return service.getLog(id);
    }

    public List<ConsumptionLog> getLogsByStockRecord(String stockId) {
        return service.getLogsByStockRecord(stockId);
    }

    public List<ConsumptionLog> getLogsByStockRecord(long stockId) {
        return service.getLogsByStockRecord(stockId);
    }
}

package com.example.demo.controller;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/consumption")
@RequiredArgsConstructor
public class ConsumptionLogController {
    private final ConsumptionLogService logService;

    @PostMapping("/{stockRecordId}")
    public ConsumptionLog logConsumption(
            @PathVariable Long stockRecordId, 
            @RequestBody ConsumptionLog log) {
        return logService.logConsumption(stockRecordId, log); // [cite: 259]
    }

    @GetMapping("/record/{stockRecordId}")
    public List<ConsumptionLog> getLogsByRecord(@PathVariable Long stockRecordId) {
        return logService.getLogsByStockRecord(stockRecordId); // [cite: 261]
    }

    @GetMapping("/{id}")
    public ConsumptionLog getLog(@PathVariable Long id) {
        return logService.getLog(id); // [cite: 263]
    }
}
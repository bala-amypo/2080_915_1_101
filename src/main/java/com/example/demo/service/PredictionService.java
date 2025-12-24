package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.PredictionRule;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.PredictionRuleRepository;
import com.example.demo.repository.StockRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PredictionService {
    @Autowired
    private PredictionRuleRepository ruleRepository;
    @Autowired
    private StockRecordRepository stockRepository;
    @Autowired
    private ConsumptionLogRepository logRepository;

    public PredictionRule createRule(PredictionRule rule) {
        rule.setCreatedAt(LocalDateTime.now());
        return ruleRepository.save(rule);
    }

    public List<PredictionRule> getAllRules() {
        return ruleRepository.findAll();
    }

    public LocalDate predictRestockDate(Long stockRecordId) {
        StockRecord sr = stockRepository.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock Record not found"));

        // Basic Simple Moving Average logic
        List<ConsumptionLog> logs = logRepository.findByStockRecordIdOrderByConsumedDateDesc(stockRecordId);
        
        double avgDailyConsumption = 0;
        if (!logs.isEmpty()) {
            avgDailyConsumption = logs.stream()
                    .mapToInt(ConsumptionLog::getConsumedQuantity)
                    .average()
                    .orElse(1.0);
        }
        
        if (avgDailyConsumption == 0) avgDailyConsumption = 1;

        // Days left = Current Qty / Avg Daily
        int daysLeft = (int) (sr.getCurrentQuantity() / avgDailyConsumption);
        
        return LocalDate.now().plusDays(daysLeft);
    }
}
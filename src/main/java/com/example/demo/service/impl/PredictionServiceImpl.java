package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.PredictionRule;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.PredictionRuleRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {

    @Autowired
    private StockRecordRepository stockRecordRepository;
    @Autowired
    private ConsumptionLogRepository consumptionLogRepository;
    @Autowired
    private PredictionRuleRepository predictionRuleRepository;

    @Override
    public LocalDate predictRestockDate(Long stockRecordId) {
        StockRecord stock = stockRecordRepository.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        List<ConsumptionLog> logs = consumptionLogRepository.findByStockRecordId(stockRecordId);

        double averageDailyUsage = logs.stream()
                .mapToInt(ConsumptionLog::getConsumedQuantity)
                .average()
                .orElse(1.0);

        if (averageDailyUsage <= 0) averageDailyUsage = 1.0;

        int currentQty = stock.getCurrentQuantity() != null ? stock.getCurrentQuantity() : 0;
        int threshold = stock.getReorderThreshold() != null ? stock.getReorderThreshold() : 0;
        
        long daysRemaining = (long) ((currentQty - threshold) / averageDailyUsage);
        if (daysRemaining < 0) daysRemaining = 0;

        return LocalDate.now().plusDays(daysRemaining);
    }

    @Override
    public PredictionRule createRule(PredictionRule rule) {
        if (rule.getAverageDaysWindow() != null && rule.getAverageDaysWindow() <= 0) {
            throw new IllegalArgumentException("Average days window must be > 0");
        }
        rule.setCreatedAt(LocalDateTime.now());
        return predictionRuleRepository.save(rule);
    }

    @Override
    public List<PredictionRule> getAllRules() {
        return predictionRuleRepository.findAll();
    }
}
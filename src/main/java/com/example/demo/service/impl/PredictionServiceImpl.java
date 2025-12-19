package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {

    private final StockRecordRepository stockRecordRepository;
    private final ConsumptionLogRepository logRepository;
    private final PredictionRuleRepository ruleRepository;

    public PredictionServiceImpl(
            StockRecordRepository stockRecordRepository,
            ConsumptionLogRepository logRepository,
            PredictionRuleRepository ruleRepository) {
        this.stockRecordRepository = stockRecordRepository;
        this.logRepository = logRepository;
        this.ruleRepository = ruleRepository;
    }

    @Override
    public PredictionRule createRule(PredictionRule rule) {

        if (rule.getAverageDaysWindow() <= 0) {
            throw new IllegalArgumentException("Invalid average window");
        }

        if (rule.getMinDailyUsage() > rule.getMaxDailyUsage()) {
            throw new IllegalArgumentException("Invalid usage range");
        }

        ruleRepository.findByRuleName(rule.getRuleName())
                .ifPresent(r -> { throw new IllegalArgumentException("Rule already exists"); });

        rule.setCreatedAt(LocalDateTime.now());
        return ruleRepository.save(rule);
    }

    @Override
    public List<PredictionRule> getAllRules() {
        return ruleRepository.findAll();
    }

    @Override
    public LocalDate predictRestockDate(Long stockRecordId) {

        StockRecord record = stockRecordRepository.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        List<ConsumptionLog> logs = logRepository.findByStockRecordId(stockRecordId);

        if (logs.isEmpty()) {
            return LocalDate.now();
        }

        double avgDailyUsage = logs.stream()
                .mapToInt(ConsumptionLog::getConsumedQuantity)
                .average()
                .orElse(0);

        if (avgDailyUsage <= 0) {
            return LocalDate.now();
        }

        int remaining = record.getCurrentQuantity() - record.getReorderThreshold();
        long days = (long) Math.ceil(remaining / avgDailyUsage);

        return LocalDate.now().plusDays(Math.max(days, 0));
    }
}

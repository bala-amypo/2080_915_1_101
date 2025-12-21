package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.PredictionRule;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.PredictionRuleRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.PredictionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {

    private final StockRecordRepository stockRecordRepository;
    private final ConsumptionLogRepository consumptionLogRepository;
    private final PredictionRuleRepository predictionRuleRepository;

    public PredictionServiceImpl(
            StockRecordRepository stockRecordRepository,
            ConsumptionLogRepository consumptionLogRepository,
            PredictionRuleRepository predictionRuleRepository
    ) {
        this.stockRecordRepository = stockRecordRepository;
        this.consumptionLogRepository = consumptionLogRepository;
        this.predictionRuleRepository = predictionRuleRepository;
    }

    @Override
    public LocalDate predictRestockDate(Long stockRecordId) {

        StockRecord stockRecord = stockRecordRepository.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        List<ConsumptionLog> logs =
                consumptionLogRepository.findByStockRecordId(stockRecordId);

        if (logs.isEmpty()) {
            return LocalDate.now();
        }

        // Use first rule (default)
        PredictionRule rule = predictionRuleRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("PredictionRule not found"));

        int daysWindow = rule.getAverageDaysWindow();

        int totalConsumed = logs.stream()
                .mapToInt(ConsumptionLog::getConsumedQuantity)
                .sum();

        double avgDailyUsage = (double) totalConsumed / daysWindow;

        if (avgDailyUsage <= 0) {
            return LocalDate.now();
        }

        int remainingQty =
                stockRecord.getCurrentQuantity() - stockRecord.getReorderThreshold();

        int daysRemaining = (int) Math.ceil(remainingQty / avgDailyUsage);

        return LocalDate.now().plusDays(Math.max(daysRemaining, 0));
    }

    @Override
    public PredictionRule createRule(PredictionRule rule) {

        if (rule.getAverageDaysWindow() <= 0) {
            throw new IllegalArgumentException("averageDaysWindow must be greater than zero");
        }

        if (rule.getMinDailyUsage() > rule.getMaxDailyUsage()) {
            throw new IllegalArgumentException("minDailyUsage must be <= maxDailyUsage");
        }

        predictionRuleRepository.findByRuleName(rule.getRuleName())
                .ifPresent(r -> {
                    throw new IllegalArgumentException("Rule name already exists");
                });

        rule.setCreatedAt(LocalDateTime.now());
        return predictionRuleRepository.save(rule);
    }

    @Override
    public List<PredictionRule> getAllRules() {
        return predictionRuleRepository.findAll();
    }
}

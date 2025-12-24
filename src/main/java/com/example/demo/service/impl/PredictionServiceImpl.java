package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.PredictionRule;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.PredictionRuleRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.PredictionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {

    private final PredictionRuleRepository ruleRepository;
    private final StockRecordRepository stockRecordRepository;

    public PredictionServiceImpl(
            PredictionRuleRepository ruleRepository,
            StockRecordRepository stockRecordRepository) {
        this.ruleRepository = ruleRepository;
        this.stockRecordRepository = stockRecordRepository;
    }

    @Override
    public PredictionRule createRule(PredictionRule rule) {

        if (rule.getAverageDaysWindow() <= 0) {
            throw new IllegalArgumentException("averageDaysWindow must be > 0");
        }
        if (rule.getMinDailyUsage() > rule.getMaxDailyUsage()) {
            throw new IllegalArgumentException("Invalid usage range");
        }

        rule.setCreatedAt(LocalDateTime.now());
        return ruleRepository.save(rule);
    }

    @Override
    public List<PredictionRule> getAllRules() {
        return ruleRepository.findAll();
    }

    @Override
    public LocalDate predictRestockDate(Long stockRecordId) {

        StockRecord stock = stockRecordRepository.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        int days = Math.max(1, stock.getCurrentQuantity() - stock.getReorderThreshold());
        return LocalDate.now().plusDays(days);
    }
}

package com.example.demo.service.impl;

import com.example.demo.dto.PredictionResponse;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.PredictionRule;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.PredictionRuleRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.PredictionService;
import org.springframework.stereotype.Service;

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
    public PredictionResponse predict(Long stockRecordId) {

        StockRecord stockRecord = stockRecordRepository.findById(stockRecordId)
                .orElseThrow(() -> new RuntimeException("Stock record not found"));

        // ✅ Correct repository method
        List<ConsumptionLog> logs =
                consumptionLogRepository.findByStockRecord_Id(stockRecordId);

        int totalConsumed = logs.stream()
                .mapToInt(ConsumptionLog::getQuantityUsed)
                .sum();

        PredictionRule rule = predictionRuleRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new RuntimeException("Prediction rule not found"));

        boolean reorderRequired =
                stockRecord.getCurrentQuantity() <= stockRecord.getReorderThreshold();

        return new PredictionResponse(
                stockRecord.getId(),
                totalConsumed,
                reorderRequired
        );
    }
}

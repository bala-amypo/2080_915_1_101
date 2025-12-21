package com.example.demo.dto;

public class PredictionResponse {

    private Long stockRecordId;
    private int totalConsumed;
    private boolean reorderRequired;

    public PredictionResponse(Long stockRecordId, int totalConsumed, boolean reorderRequired) {
        this.stockRecordId = stockRecordId;
        this.totalConsumed = totalConsumed;
        this.reorderRequired = reorderRequired;
    }

    public Long getStockRecordId() {
        return stockRecordId;
    }

    public int getTotalConsumed() {
        return totalConsumed;
    }

    public boolean isReorderRequired() {
        return reorderRequired;
    }
}

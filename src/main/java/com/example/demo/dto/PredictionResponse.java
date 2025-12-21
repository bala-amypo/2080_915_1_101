package com.example.demo.dto;

import java.time.LocalDate;

public class PredictionResponse {

    private LocalDate predictedRestockDate;

    public PredictionResponse(LocalDate predictedRestockDate) {
        this.predictedRestockDate = predictedRestockDate;
    }

    public LocalDate getPredictedRestockDate() {
        return predictedRestockDate;
    }

    public void setPredictedRestockDate(LocalDate predictedRestockDate) {
        this.predictedRestockDate = predictedRestockDate;
    }
}

package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PredictionResponse {
    private Long stockRecordId;
    private LocalDate predictedRestockDate;
}
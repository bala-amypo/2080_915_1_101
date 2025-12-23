package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class PredictionResponseDto {

    private Long stockRecordId;
    private LocalDate predictedRestockDate;
}

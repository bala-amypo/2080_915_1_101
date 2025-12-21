package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class PredictionRule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ruleName;        // Matches getRuleName()
    private Integer averageDaysWindow; // Matches getAverageDaysWindow()
    private Double minDailyUsage;   // Matches getMinDailyUsage()
    private Double maxDailyUsage;   // Matches getMaxDailyUsage()
    private LocalDateTime createdAt;
}
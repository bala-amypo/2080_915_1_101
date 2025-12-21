package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prediction_rules") // [cite: 62]
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PredictionRule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // [cite: 64]
    @Column(unique = true)
    private String ruleName; // [cite: 65]
    private Integer averageDaysWindow; // [cite: 66]
    private Integer minDailyUsage; // [cite: 67]
    private Integer maxDailyUsage; // [cite: 68]
    private LocalDateTime createdAt; // [cite: 69]
}
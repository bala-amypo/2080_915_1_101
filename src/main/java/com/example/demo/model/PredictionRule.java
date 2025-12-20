package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prediction_rules")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PredictionRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // [cite: 64]

    @Column(unique = true, nullable = false)
    private String ruleName; // [cite: 65, 71]

    private Integer averageDaysWindow; // Must be > 0 [cite: 66, 70]

    private Integer minDailyUsage; // [cite: 67]

    private Integer maxDailyUsage; // Must be >= minDailyUsage [cite: 68, 70]

    private LocalDateTime createdAt; // [cite: 69]
}
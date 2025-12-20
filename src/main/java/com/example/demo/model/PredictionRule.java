package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PredictionRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;
    private int averageDaysWindow;
    private int minDailyUsage;
    private int maxDailyUsage;
    private LocalDateTime createdAt;

    public String getRuleName() { return ruleName; }
    public int getAverageDaysWindow() { return averageDaysWindow; }
    public int getMinDailyUsage() { return minDailyUsage; }
    public int getMaxDailyUsage() { return maxDailyUsage; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

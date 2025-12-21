package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PredictionRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String ruleName;

    private int averageDaysWindow;

    private int minDailyUsage;

    private int maxDailyUsage;

    private LocalDateTime createdAt;

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public int getAverageDaysWindow() {
        return averageDaysWindow;
    }

    public void setAverageDaysWindow(int averageDaysWindow) {
        this.averageDaysWindow = averageDaysWindow;
    }

    public int getMinDailyUsage() {
        return minDailyUsage;
    }

    public void setMinDailyUsage(int minDailyUsage) {
        this.minDailyUsage = minDailyUsage;
    }

    public int getMaxDailyUsage() {
        return maxDailyUsage;
    }

    public void setMaxDailyUsage(int maxDailyUsage) {
        this.maxDailyUsage = maxDailyUsage;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

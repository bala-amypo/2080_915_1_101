package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class PredictionRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;
    private int threshold;

    public PredictionRule() {}

    public Long getId() { return id; }
    public String getRuleName() { return ruleName; }
    public int getThreshold() { return threshold; }

    public void setId(Long id) { this.id = id; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }
    public void setThreshold(int threshold) { this.threshold = threshold; }
}

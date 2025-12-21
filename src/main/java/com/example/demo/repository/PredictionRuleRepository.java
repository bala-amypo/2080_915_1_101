package com.example.demo.repository;

import com.example.demo.model.PredictionRule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PredictionRuleRepository extends JpaRepository<PredictionRule, Long> { // [cite: 123]
    Optional<PredictionRule> findByRuleName(String ruleName); // [cite: 124]
}
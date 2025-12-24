package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.example.demo.model.*;
public interface PredictionRuleRepository extends JpaRepository<PredictionRule, Long> {
    Optional<PredictionRule> findByRuleName(String name);
}

package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
package com.example.demo.repository;

import com.example.demo.model.PredictionRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredictionRuleRepository extends JpaRepository<PredictionRule, Long> {}

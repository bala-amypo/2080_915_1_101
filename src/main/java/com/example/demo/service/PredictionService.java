package com.example.demo.service;

import java.util.*;
import java.time.LocalDate;
import com.example.demo.model.*;
public interface PredictionService {
    PredictionRule createRule(PredictionRule rule);
    List<PredictionRule> getAllRules();
    LocalDate predictRestockDate(Long stockId);
}

package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ConsumptionLogServiceImpl implements ConsumptionLogService {
    private final ConsumptionLogRepository repo;
    public ConsumptionLogServiceImpl(ConsumptionLogRepository repo) { this.repo = repo; }
    public ConsumptionLog save(ConsumptionLog c) { return repo.save(c); }
    public List<ConsumptionLog> getAll() { return repo.findAll(); }
}

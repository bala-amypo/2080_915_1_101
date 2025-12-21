package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class StockRecordServiceImpl implements StockRecordService {
    private final StockRecordRepository repo;
    public StockRecordServiceImpl(StockRecordRepository repo) { this.repo = repo; }
    public StockRecord save(StockRecord s) { return repo.save(s); }
    public List<StockRecord> getAll() { return repo.findAll(); }
}

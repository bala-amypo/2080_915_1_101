package com.example.demo.service;

import com.example.demo.model.StockRecord;
import java.util.List;

public interface StockRecordService {
    StockRecord save(StockRecord record);
    List<StockRecord> getAll();
}

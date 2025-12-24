package com.example.demo.service;

import java.util.*;
import java.time.LocalDate;
import com.example.demo.model.*;
public interface StockRecordService {
    StockRecord createStockRecord(Long pid, Long wid, StockRecord r);
    StockRecord getStockRecord(Long id);
    List<StockRecord> getRecordsBy_product(Long pid);
    List<StockRecord> getRecordsByWarehouse(Long wid);
}

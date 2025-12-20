package com.example.demo.service;

import com.example.demo.model.Warehouse;
import java.util.List;

public interface WarehouseService {
    Warehouse createWarehouse(Warehouse warehouse); // [cite: 162]
    Warehouse getWarehouse(Long id); // [cite: 163]
    List<Warehouse> getAllWarehouses(); // [cite: 164]
}
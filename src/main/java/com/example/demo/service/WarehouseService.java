package com.example.demo.service;

import com.example.demo.model.Warehouse;
import java.util.List;

public interface WarehouseService {

    Warehouse getWarehouse(String id);
    Warehouse getWarehouse(long id);

    List<Warehouse> getAllWarehouses();
}

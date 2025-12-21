package com.example.demo.service;

import com.example.demo.model.Warehouse;

import java.util.List;
import java.util.Optional;

public interface WarehouseService {

    Warehouse saveWarehouse(Warehouse warehouse);

    Optional<Warehouse> getByWarehouseName(String warehouseName);

    List<Warehouse> getAllWarehouses();
}

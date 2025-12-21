package com.example.demo.service;

import com.example.demo.model.Warehouse;
import java.util.List;

public interface WarehouseService {
    Warehouse save(Warehouse warehouse);
    List<Warehouse> getAll();
}

package com.example.demo.service;

import java.util.*;
import java.time.LocalDate;
import com.example.demo.model.*;
public interface WarehouseService {
    Warehouse createWarehouse(Warehouse w);
    Warehouse getWarehouse(Long id);
    List<Warehouse> getAllWarehouses();
}

package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository repo;
    public WarehouseServiceImpl(WarehouseRepository repo) { this.repo = repo; }
    public Warehouse save(Warehouse w) { return repo.save(w); }
    public List<Warehouse> getAll() { return repo.findAll(); }
}

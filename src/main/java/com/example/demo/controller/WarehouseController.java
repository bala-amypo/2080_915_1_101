package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {
    private final WarehouseService service;
    public WarehouseController(WarehouseService service){ this.service = service; }
    @PostMapping public Warehouse save(@RequestBody Warehouse w){ return service.save(w); }
    @GetMapping public List<Warehouse> get(){ return service.getAll(); }
}

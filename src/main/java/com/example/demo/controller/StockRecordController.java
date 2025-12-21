package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/stocks")
public class StockRecordController {
    private final StockRecordService service;
    public StockRecordController(StockRecordService service){ this.service = service; }
    @PostMapping public StockRecord save(@RequestBody StockRecord s){ return service.save(s); }
    @GetMapping public List<StockRecord> get(){ return service.getAll(); }
}

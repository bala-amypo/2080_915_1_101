package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/consumption")
public class ConsumptionLogController {
    private final ConsumptionLogService service;
    public ConsumptionLogController(ConsumptionLogService service){ this.service = service; }
    @PostMapping public ConsumptionLog save(@RequestBody ConsumptionLog c){ return service.save(c); }
    @GetMapping public List<ConsumptionLog> get(){ return service.getAll(); }
}

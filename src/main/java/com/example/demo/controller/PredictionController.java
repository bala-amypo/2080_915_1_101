package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/predict")
public class PredictionController {
    private final PredictionService service;
    public PredictionController(PredictionService service){ this.service = service; }
    @GetMapping public PredictionResponse predict(){ return service.predict(); }
}

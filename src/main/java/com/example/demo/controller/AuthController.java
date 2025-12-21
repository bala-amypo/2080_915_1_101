package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/auth") // [cite: 275]
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register") public Object register(@RequestBody UserRegisterDto d) { return userService.register(d); } // [cite: 279]
    @PostMapping("/login") public AuthResponse login(@RequestBody AuthRequest r) { return userService.login(r); } // [cite: 282]
}
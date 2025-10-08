package com.example.library.controller;

import com.example.library.dto.auth.*;
import com.example.library.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/Login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/Register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return  authService.register(request);
    }
}

package com.example.userservice.controller;

import com.example.userservice.dto.LoginRequest;
import com.example.userservice.dto.RegisterRequest;
import com.example.userservice.dto.ResponseApi;
import com.example.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ResponseApi<Void>> register(@RequestBody RegisterRequest request) {
        try {
            return authService.register(request);
        } catch (Exception e) {
            ResponseApi<Void> response = new ResponseApi<>(false, "Registration failed: " + e.getMessage(), null);
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseApi<String>> login(@RequestBody LoginRequest request) {
        try {
            return authService.login(request);
        } catch (Exception e) {
            ResponseApi<String> response = new ResponseApi<>(false, "Login failed: " + e.getMessage(), null);
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
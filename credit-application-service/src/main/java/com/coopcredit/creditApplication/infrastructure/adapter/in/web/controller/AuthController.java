package com.coopcredit.creditApplication.infrastructure.adapter.in.web.controller;

import com.coopcredit.creditApplication.infrastructure.adapter.in.web.dto.AuthenticationRequest;
import com.coopcredit.creditApplication.infrastructure.adapter.in.web.dto.AuthenticationResponse;
import com.coopcredit.creditApplication.infrastructure.adapter.in.web.dto.RegisterRequest;
import com.coopcredit.creditApplication.infrastructure.security.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}

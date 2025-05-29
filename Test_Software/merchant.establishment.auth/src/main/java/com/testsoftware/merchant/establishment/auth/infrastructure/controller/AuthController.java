package com.testsoftware.merchant.establishment.auth.infrastructure.controller;

import com.testsoftware.merchant.establishment.auth.application.service.AuthService;
import com.testsoftware.merchant.establishment.auth.infrastructure.dto.GeneralResponse;
import com.testsoftware.merchant.establishment.auth.infrastructure.dto.LoginRequest;
import com.testsoftware.merchant.establishment.auth.infrastructure.dto.LoginResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Login of user")
    @PostMapping("/login")
    public ResponseEntity<GeneralResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(GeneralResponse.success(response, "Login succesfull"));
    }
}
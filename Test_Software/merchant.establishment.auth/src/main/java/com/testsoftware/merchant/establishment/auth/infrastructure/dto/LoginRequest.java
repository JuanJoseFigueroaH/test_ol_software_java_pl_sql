package com.testsoftware.merchant.establishment.auth.infrastructure.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
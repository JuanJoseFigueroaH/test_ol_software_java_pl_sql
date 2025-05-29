package com.testsoftware.merchant.establishment.auth.infrastructure.dto;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Data
public class MerchantRequest {

    @NotBlank
    private String name;

    @NotNull
    private Long municipalityId;

    private String phone;

    @Email
    private String email;

    @NotNull
    private LocalDate registrationDate;

    @NotBlank
    private String status;
}

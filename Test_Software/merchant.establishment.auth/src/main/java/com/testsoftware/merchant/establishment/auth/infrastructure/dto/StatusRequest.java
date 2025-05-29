package com.testsoftware.merchant.establishment.auth.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StatusRequest {

    @NotBlank
    private String status;
}

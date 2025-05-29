package com.testsoftware.merchant.establishment.auth.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MerchantResponse {

    private Long id;

    private String name;

    private Long municipalityId;

    private String municipalityName;

    private String phone;

    private String email;

    private LocalDate registrationDate;

    private String status;
}

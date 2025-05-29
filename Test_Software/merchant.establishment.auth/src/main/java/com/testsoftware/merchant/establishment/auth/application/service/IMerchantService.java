package com.testsoftware.merchant.establishment.auth.application.service;

import com.testsoftware.merchant.establishment.auth.infrastructure.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;

public interface IMerchantService {
    Page<MerchantResponse> list(Optional<String> name, Optional<String> status, Optional<LocalDate> registrationDate, Pageable pageable);
    MerchantResponse getById(Long id);
    MerchantResponse create(MerchantRequest request, String user);
    MerchantResponse update(Long id, MerchantRequest request, String user);
    void changeStatus(Long id, String status, String user);
    void delete(Long id);
}

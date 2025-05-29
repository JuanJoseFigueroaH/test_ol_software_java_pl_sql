package com.testsoftware.merchant.establishment.auth.infrastructure.controller;

import com.testsoftware.merchant.establishment.auth.application.service.MerchantService;
import com.testsoftware.merchant.establishment.auth.infrastructure.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.testsoftware.merchant.establishment.auth.infrastructure.dto.GeneralResponse;

import jakarta.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/merchants")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping
    public ResponseEntity<GeneralResponse<Page<MerchantResponse>>> list(
            @RequestParam Optional<String> name,
            @RequestParam Optional<String> status,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> registrationDate,
            Pageable pageable) {

        Page<MerchantResponse> result = merchantService.list(name, status, registrationDate, pageable);
        return ResponseEntity.ok(GeneralResponse.success(result, "Paginated list of merchants"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<MerchantResponse>> getById(@PathVariable Long id) {
        MerchantResponse merchant = merchantService.getById(id);
        return ResponseEntity.ok(GeneralResponse.success(merchant, "Merchant found"));
    }

    @PostMapping
    public ResponseEntity<GeneralResponse<MerchantResponse>> create(
            @RequestBody @Valid MerchantRequest request, Principal principal) {

        MerchantResponse created = merchantService.create(request, principal.getName());
        return ResponseEntity.status(201).body(GeneralResponse.success(created, "Merchant created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse<MerchantResponse>> update(
            @PathVariable Long id,
            @RequestBody @Valid MerchantRequest request,
            Principal principal) {

        MerchantResponse updated = merchantService.update(id, request, principal.getName());
        return ResponseEntity.ok(GeneralResponse.success(updated, "Merchant updated successfully"));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<GeneralResponse<Void>> changeStatus(
            @PathVariable Long id,
            @RequestBody StatusRequest statusRequest,
            Principal principal) {

        merchantService.changeStatus(id, statusRequest.getStatus(), principal.getName());
        return ResponseEntity.ok(GeneralResponse.success(null, "Status updated successfully"));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GeneralResponse<Void>> delete(@PathVariable Long id) {
        merchantService.delete(id);
        return ResponseEntity.ok(GeneralResponse.success(null, "Merchant deleted successfully"));
    }
}

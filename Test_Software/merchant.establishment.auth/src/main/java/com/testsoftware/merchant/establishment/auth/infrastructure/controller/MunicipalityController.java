package com.testsoftware.merchant.establishment.auth.infrastructure.controller;

import com.testsoftware.merchant.establishment.auth.application.service.MunicipalityService;
import com.testsoftware.merchant.establishment.auth.domain.model.Municipality;
import com.testsoftware.merchant.establishment.auth.infrastructure.dto.GeneralResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/municipalities")
@RequiredArgsConstructor
public class MunicipalityController {

    private final MunicipalityService municipalityService;

    @GetMapping
    public ResponseEntity<GeneralResponse<List<Municipality>>> getMunicipalities() {
        List<Municipality> municipalities = municipalityService.getMunicipalities();
        return ResponseEntity.ok(GeneralResponse.success(municipalities, "Municipality list retrieved successfully"));
    }
}

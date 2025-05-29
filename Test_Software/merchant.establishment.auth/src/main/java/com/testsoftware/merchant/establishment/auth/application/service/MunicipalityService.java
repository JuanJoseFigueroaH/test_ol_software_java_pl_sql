package com.testsoftware.merchant.establishment.auth.application.service;

import com.testsoftware.merchant.establishment.auth.domain.model.Municipality;
import com.testsoftware.merchant.establishment.auth.domain.port.MunicipalityRepository;
import com.testsoftware.merchant.establishment.auth.infrastructure.cache.InMemoryMunicipalityCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MunicipalityService {

    private final MunicipalityRepository municipalityRepository;
    private final InMemoryMunicipalityCache cache;

    public List<Municipality> getMunicipalities() {
        List<Municipality> cached = cache.getCachedMunicipalities();
        if (cached != null)
            return cached;

        List<Municipality> municipalities = municipalityRepository.findAll();
        cache.cacheMunicipalities(municipalities);
        return municipalities;
    }
}

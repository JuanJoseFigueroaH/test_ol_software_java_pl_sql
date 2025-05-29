package com.testsoftware.merchant.establishment.auth.infrastructure.cache;

import com.testsoftware.merchant.establishment.auth.domain.model.Municipality;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InMemoryMunicipalityCache {

    private List<Municipality> cachedMunicipalities;

    public List<Municipality> getCachedMunicipalities() {
        return cachedMunicipalities;
    }

    public void cacheMunicipalities(List<Municipality> municipalities) {
        this.cachedMunicipalities = municipalities;
    }
}

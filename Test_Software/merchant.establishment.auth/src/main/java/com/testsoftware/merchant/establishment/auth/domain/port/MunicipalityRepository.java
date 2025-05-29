package com.testsoftware.merchant.establishment.auth.domain.port;

import com.testsoftware.merchant.establishment.auth.domain.model.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {}

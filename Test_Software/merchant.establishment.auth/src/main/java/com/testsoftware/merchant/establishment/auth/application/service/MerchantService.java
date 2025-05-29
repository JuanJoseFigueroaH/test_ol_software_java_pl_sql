package com.testsoftware.merchant.establishment.auth.application.service;

import com.testsoftware.merchant.establishment.auth.application.service.MerchantService;
import com.testsoftware.merchant.establishment.auth.domain.model.Auth;
import com.testsoftware.merchant.establishment.auth.domain.model.Merchant;
import com.testsoftware.merchant.establishment.auth.domain.model.Municipality;
import com.testsoftware.merchant.establishment.auth.domain.port.MerchantRepository;
import com.testsoftware.merchant.establishment.auth.domain.port.MunicipalityRepository;
import com.testsoftware.merchant.establishment.auth.domain.port.UserRepository;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.testsoftware.merchant.establishment.auth.infrastructure.dto.MerchantRequest;
import com.testsoftware.merchant.establishment.auth.infrastructure.dto.MerchantResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MerchantService implements IMerchantService {

        private final MerchantRepository merchantRepository;
        private final UserRepository userRepository;
        private final MunicipalityRepository municipalityRepository;

        @Override
        public Page<MerchantResponse> list(Optional<String> nameOpt, Optional<String> statusOpt,
                        Optional<LocalDate> registrationDateOpt, Pageable pageable) {

                String name = nameOpt.map(String::trim).orElse(null);
                String status = statusOpt.map(String::trim).orElse(null);
                LocalDate registrationDate = registrationDateOpt.orElse(null);

                int page = pageable.getPageNumber();
                int size = pageable.getPageSize();
                int startRow = page * size;
                int endRow = startRow + size;

                List<Merchant> merchants = merchantRepository.findByCustomPagination(
                                name, status, registrationDate, startRow, endRow);

                long total = merchantRepository.countByFilters(name, status, registrationDate);

                List<MerchantResponse> responses = merchants.stream()
                                .map(this::mapToDto)
                                .toList();

                return new PageImpl<>(responses, pageable, total);
        }

        @Override
        public MerchantResponse getById(Long id) {
                Merchant merchant = merchantRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Merchant not found"));
                return mapToDto(merchant);
        }

        @Override
        public MerchantResponse create(MerchantRequest request, String user) {
                Auth userSearch = userRepository.findByEmail(user)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                Municipality municipality = municipalityRepository.findById(request.getMunicipalityId())
                                .orElseThrow(() -> new RuntimeException("Municipality not found"));

                Merchant merchant = Merchant.builder()
                                .name(request.getName())
                                .municipality(municipality)
                                .phone(request.getPhone())
                                .email(request.getEmail())
                                .registrationDate(request.getRegistrationDate())
                                .status(Merchant.Status.valueOf(request.getStatus().toUpperCase()))
                                .updatedByUser(userSearch.getId())
                                .build();

                return mapToDto(merchantRepository.save(merchant));
        }

        @Override
        public MerchantResponse update(Long id, MerchantRequest request, String user) {
                Auth userSearch = userRepository.findByEmail(user)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                Merchant merchant = merchantRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Merchant not found"));

                Municipality municipality = municipalityRepository.findById(request.getMunicipalityId())
                                .orElseThrow(() -> new RuntimeException("Municipality not found"));

                merchant.setName(request.getName());
                merchant.setMunicipality(municipality);
                merchant.setPhone(request.getPhone());
                merchant.setEmail(request.getEmail());
                merchant.setRegistrationDate(request.getRegistrationDate());
                merchant.setStatus(Merchant.Status.valueOf(request.getStatus().toUpperCase()));
                merchant.setLastUpdated(LocalDate.now());
                merchant.setUpdatedByUser(userSearch.getId());

                return mapToDto(merchantRepository.save(merchant));
        }

        @Override
        public void changeStatus(Long id, String status, String user) {
                Auth userSearch = userRepository.findByEmail(user)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                Merchant merchant = merchantRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Merchant not found"));

                merchant.setStatus(Merchant.Status.valueOf(status.toUpperCase()));
                merchant.setLastUpdated(LocalDate.now());
                merchant.setUpdatedByUser(Long.valueOf(userSearch.getId()));
                merchantRepository.save(merchant);
        }

        @Override
        public void delete(Long id) {
                Merchant merchant = merchantRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Merchant not found"));
                merchantRepository.delete(merchant);
        }

        private MerchantResponse mapToDto(Merchant m) {
                return MerchantResponse.builder()
                                .id(m.getId())
                                .name(m.getName())
                                .municipalityId(m.getMunicipality().getId())
                                .municipalityName(m.getMunicipality().getName())
                                .phone(m.getPhone())
                                .email(m.getEmail())
                                .registrationDate(m.getRegistrationDate())
                                .status(m.getStatus().name())
                                .build();
        }
}

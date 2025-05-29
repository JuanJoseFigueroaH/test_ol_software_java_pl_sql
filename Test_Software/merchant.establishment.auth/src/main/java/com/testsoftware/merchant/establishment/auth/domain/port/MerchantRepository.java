package com.testsoftware.merchant.establishment.auth.domain.port;

import com.testsoftware.merchant.establishment.auth.domain.model.Merchant;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    @Query(value = """
            SELECT * FROM (
                SELECT m.*, ROWNUM rn FROM (
                    SELECT * FROM merchant
                    WHERE (:name IS NULL OR LOWER(business_name) LIKE '%' || LOWER(:name) || '%')
                      AND (:status IS NULL OR status = :status)
                      AND (:registrationDate IS NULL OR registration_date = :registrationDate)
                    ORDER BY merchant_id
                ) m WHERE ROWNUM <= :endRow
            ) WHERE rn > :startRow
            """, nativeQuery = true)
    List<Merchant> findByCustomPagination(
            @Param("name") String name,
            @Param("status") String status,
            @Param("registrationDate") LocalDate registrationDate,
            @Param("startRow") int startRow,
            @Param("endRow") int endRow);

    @Query(value = """
            SELECT COUNT(*) FROM merchant
            WHERE (:name IS NULL OR LOWER(business_name) LIKE '%' || LOWER(:name) || '%')
              AND (:status IS NULL OR status = :status)
              AND (:registrationDate IS NULL OR registration_date = :registrationDate)
            """, nativeQuery = true)
    long countByFilters(
            @Param("name") String name,
            @Param("status") String status,
            @Param("registrationDate") LocalDate registrationDate);
}

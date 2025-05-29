package com.testsoftware.merchant.establishment.auth.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "municipality")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Municipality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}

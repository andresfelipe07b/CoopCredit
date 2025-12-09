package com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "affiliates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AffiliateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @Column(unique = true, nullable = false)
    private String document;

    private Double salary;
    private String address;
    private LocalDate startDate;
    private boolean active;
}

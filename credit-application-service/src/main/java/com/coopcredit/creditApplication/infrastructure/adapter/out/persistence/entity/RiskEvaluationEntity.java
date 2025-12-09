package com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "risk_evaluations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RiskEvaluationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", unique = true)
    private CreditApplicationEntity application;

    private Integer score;
    private String reason;

    @Column(name = "risk_level")
    private String riskLevel;

    private LocalDateTime createdAt;
}

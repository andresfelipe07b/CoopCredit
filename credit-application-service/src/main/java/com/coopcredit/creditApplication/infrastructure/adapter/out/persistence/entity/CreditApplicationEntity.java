package com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.entity;

import com.coopcredit.creditApplication.domain.model.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "credit_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "affiliate_id")
    private AffiliateEntity affiliate;

    private Double amount;
    private Integer term;

    @Column(name = "base_rate")
    private Double baseRate;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "application", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private RiskEvaluationEntity evaluation;
}

package com.coopcredit.creditApplication.domain.model;

import java.time.LocalDateTime;

public class CreditApplication {
    private Long id;
    private Long affiliateId;
    private Double amount;
    private Integer term; // in months
    private Double baseRate;
    private ApplicationStatus status;
    private LocalDateTime createdAt;

    private RiskEvaluation evaluation; // Rich domain model

    public CreditApplication() {
    }

    public CreditApplication(Long id, Long affiliateId, Double amount, Integer term, Double baseRate,
            ApplicationStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.affiliateId = affiliateId;
        this.amount = amount;
        this.term = term;
        this.baseRate = baseRate;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAffiliateId() {
        return affiliateId;
    }

    public void setAffiliateId(Long affiliateId) {
        this.affiliateId = affiliateId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Double getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(Double baseRate) {
        this.baseRate = baseRate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public RiskEvaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(RiskEvaluation evaluation) {
        this.evaluation = evaluation;
    }
}

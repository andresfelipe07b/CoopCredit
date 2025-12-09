package com.coopcredit.creditApplication.domain.model;

import java.time.LocalDateTime;

public class RiskEvaluation {
    private Long id;
    private Long applicationId;
    private Integer score;
    private String reason;
    private String riskLevel;
    private LocalDateTime createdAt;

    public RiskEvaluation() {
    }

    public RiskEvaluation(Long id, Long applicationId, Integer score, String reason, String riskLevel,
            LocalDateTime createdAt) {
        this.id = id;
        this.applicationId = applicationId;
        this.score = score;
        this.reason = reason;
        this.riskLevel = riskLevel;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

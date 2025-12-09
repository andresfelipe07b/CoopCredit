package com.coopcredit.riskCentral.dto;

import lombok.Data;

@Data
public class RiskEvaluationRequest {
    private String document;
    private Double amount;
}

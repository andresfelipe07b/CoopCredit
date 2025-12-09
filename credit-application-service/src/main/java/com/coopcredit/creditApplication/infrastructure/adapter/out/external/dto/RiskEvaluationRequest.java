package com.coopcredit.creditApplication.infrastructure.adapter.out.external.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RiskEvaluationRequest {
    private String document;
    private Double amount;
    private Integer term;
}

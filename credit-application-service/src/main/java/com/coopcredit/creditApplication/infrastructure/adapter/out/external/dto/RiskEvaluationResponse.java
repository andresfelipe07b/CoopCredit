package com.coopcredit.creditApplication.infrastructure.adapter.out.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RiskEvaluationResponse {
    @JsonProperty("documento")
    private String document;

    private Integer score;

    @JsonProperty("nivelRiesgo")
    private String riskLevel;

    @JsonProperty("detalle")
    private String reason;
}

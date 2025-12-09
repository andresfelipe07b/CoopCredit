package com.coopcredit.riskCentral.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RiskEvaluationResponse {
    @JsonProperty("documento")
    private String document;

    private Integer score;

    private String nivelRiesgo; // ALTO, MEDIO, BAJO

    @JsonProperty("detalle")
    private String reason;
}

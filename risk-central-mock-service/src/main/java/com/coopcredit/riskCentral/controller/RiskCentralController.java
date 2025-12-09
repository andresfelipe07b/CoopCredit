package com.coopcredit.riskCentral.controller;

import com.coopcredit.riskCentral.dto.RiskEvaluationRequest;
import com.coopcredit.riskCentral.dto.RiskEvaluationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RiskCentralController {

    @PostMapping("/risk-evaluation")
    public ResponseEntity<RiskEvaluationResponse> evaluate(@RequestBody RiskEvaluationRequest request) {
        // Business Logic: Numeric Seed (hash mod 1000)
        int seed = Math.abs(request.getDocument().hashCode() % 1000);

        // Generate score between 300 and 950 based on seed
        // Normalize seed (0-999) to range (300-950) -> amplitude 650
        int score = 300 + (seed * 650 / 999);

        String nivelRiesgo;
        String detalle;

        if (score <= 500) {
            nivelRiesgo = "ALTO_RIESGO";
            // Requirement: 300-500 -> ALTO RIESGO
            nivelRiesgo = "ALTO RIESGO";
            detalle = "Historial crediticio deficiente. Alta probabilidad de impago.";
        } else if (score <= 700) {
            nivelRiesgo = "MEDIO RIESGO";
            detalle = "Historial crediticio moderado. Capacidad de pago limitada.";
        } else {
            nivelRiesgo = "BAJO RIESGO";
            detalle = "Excelente historial crediticio. Alta capacidad de pago.";
        }

        return ResponseEntity.ok(RiskEvaluationResponse.builder()
                .document(request.getDocument())
                .score(score)
                .nivelRiesgo(nivelRiesgo)
                .reason(detalle)
                .build());
    }
}

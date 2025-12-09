package com.coopcredit.creditApplication.infrastructure.adapter.out.external;

import com.coopcredit.creditApplication.domain.model.RiskEvaluation;
import com.coopcredit.creditApplication.domain.port.out.RiskEvaluationExternalPort;
import com.coopcredit.creditApplication.infrastructure.adapter.out.external.dto.RiskEvaluationRequest;
import com.coopcredit.creditApplication.infrastructure.adapter.out.external.dto.RiskEvaluationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RiskEvaluationExternalAdapter implements RiskEvaluationExternalPort {

    private final RestTemplate restTemplate;

    @Value("${RISK_SERVICE_URL:http://localhost:8081}")
    private String riskServiceUrl;

    public RiskEvaluationExternalAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public RiskEvaluation evaluate(String personDocument, Double amount, Integer term) {
        // Create request for risk service

        RiskEvaluationRequest request = RiskEvaluationRequest.builder()
                .document(personDocument)
                .amount(amount)
                .term(term)
                .build();

        try {
            RiskEvaluationResponse response = restTemplate.postForObject(
                    riskServiceUrl + "/risk-evaluation",
                    request,
                    RiskEvaluationResponse.class);

            if (response != null) {
                RiskEvaluation evaluation = new RiskEvaluation();
                evaluation.setScore(response.getScore());
                evaluation.setReason(response.getReason());
                evaluation.setRiskLevel(response.getRiskLevel());
                return evaluation;
            } else {
                throw new RuntimeException("Empty response from Risk Service");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to call Risk Service: " + e.getMessage());
        }
    }
}

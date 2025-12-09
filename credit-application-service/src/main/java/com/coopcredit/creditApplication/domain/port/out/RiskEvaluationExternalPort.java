package com.coopcredit.creditApplication.domain.port.out;

import com.coopcredit.creditApplication.domain.model.RiskEvaluation;

public interface RiskEvaluationExternalPort {
    // Returns a score, or the full evaluation object constructed from external data
    RiskEvaluation evaluate(String personDocument, Double amount, Integer term);
}

package com.coopcredit.creditApplication.domain.port.out;

import com.coopcredit.creditApplication.domain.model.RiskEvaluation;
import java.util.Optional;

public interface RiskEvaluationRepositoryPort {
    RiskEvaluation save(RiskEvaluation riskEvaluation);
    Optional<RiskEvaluation> findByApplicationId(Long applicationId);
}

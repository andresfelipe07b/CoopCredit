package com.coopcredit.creditApplication.domain.port.in;

import com.coopcredit.creditApplication.domain.model.CreditApplication;

public interface EvaluateCreditApplicationUseCase {
    void evaluate(Long creditApplicationId);
}

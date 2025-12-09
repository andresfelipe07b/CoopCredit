package com.coopcredit.creditApplication.domain.port.in;

import com.coopcredit.creditApplication.domain.model.CreditApplication;

public interface CreateCreditApplicationUseCase {
    CreditApplication create(CreditApplication creditApplication);
}

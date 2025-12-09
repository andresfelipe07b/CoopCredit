package com.coopcredit.creditApplication.domain.port.in;

import com.coopcredit.creditApplication.domain.model.CreditApplication;
import java.util.List;

public interface GetCreditApplicationUseCase {
    CreditApplication getById(Long id);

    List<CreditApplication> getAll();

    List<CreditApplication> getByAffiliateId(Long affiliateId);
}

package com.coopcredit.creditApplication.domain.port.in;

import com.coopcredit.creditApplication.domain.model.Affiliate;

public interface UpdateAffiliateUseCase {
    Affiliate update(Long id, Affiliate affiliate);
}

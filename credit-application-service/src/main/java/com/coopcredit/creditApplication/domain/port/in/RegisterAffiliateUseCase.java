package com.coopcredit.creditApplication.domain.port.in;

import com.coopcredit.creditApplication.domain.model.Affiliate;

public interface RegisterAffiliateUseCase {
    Affiliate register(Affiliate affiliate);
}

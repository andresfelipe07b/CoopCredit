package com.coopcredit.creditApplication.domain.usecase;

import com.coopcredit.creditApplication.domain.model.Affiliate;
import com.coopcredit.creditApplication.domain.port.in.RegisterAffiliateUseCase;
import com.coopcredit.creditApplication.domain.port.out.AffiliateRepositoryPort;

public class RegisterAffiliateUseCaseImpl implements RegisterAffiliateUseCase {

    private final AffiliateRepositoryPort affiliateRepositoryPort;

    public RegisterAffiliateUseCaseImpl(AffiliateRepositoryPort affiliateRepositoryPort) {
        this.affiliateRepositoryPort = affiliateRepositoryPort;
    }

    @Override
    public Affiliate register(Affiliate affiliate) {

        if (affiliateRepositoryPort.existsByDocument(affiliate.getDocument())) {
            throw new IllegalArgumentException("Document already exists: " + affiliate.getDocument());
        }

        if (affiliate.getSalary() == null || affiliate.getSalary() <= 0) {
            throw new IllegalArgumentException("Salary must be greater than 0");
        }

        return affiliateRepositoryPort.save(affiliate);
    }
}

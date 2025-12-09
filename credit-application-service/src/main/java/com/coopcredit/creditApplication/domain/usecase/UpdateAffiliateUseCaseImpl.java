package com.coopcredit.creditApplication.domain.usecase;

import com.coopcredit.creditApplication.domain.model.Affiliate;
import com.coopcredit.creditApplication.domain.port.in.UpdateAffiliateUseCase;
import com.coopcredit.creditApplication.domain.port.out.AffiliateRepositoryPort;

public class UpdateAffiliateUseCaseImpl implements UpdateAffiliateUseCase {

    private final AffiliateRepositoryPort affiliateRepositoryPort;

    public UpdateAffiliateUseCaseImpl(AffiliateRepositoryPort affiliateRepositoryPort) {
        this.affiliateRepositoryPort = affiliateRepositoryPort;
    }

    @Override
    public Affiliate update(Long id, Affiliate updatedInfo) {
        Affiliate existing = affiliateRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Affiliate not found"));

        // Update allowed fields (Basic Info)
        if (updatedInfo.getName() != null)
            existing.setName(updatedInfo.getName());
        if (updatedInfo.getEmail() != null)
            existing.setEmail(updatedInfo.getEmail());
        if (updatedInfo.getAddress() != null)
            existing.setAddress(updatedInfo.getAddress());
        if (updatedInfo.getSalary() != null) {
            if (updatedInfo.getSalary() <= 0) {
                throw new IllegalArgumentException("Salary must be greater than 0");
            }
            existing.setSalary(updatedInfo.getSalary());
        }
        // Document, ID, StartDate usually are not editable in basic info or require
        // special process

        return affiliateRepositoryPort.save(existing);
    }
}

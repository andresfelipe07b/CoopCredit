package com.coopcredit.creditApplication.domain.usecase;

import com.coopcredit.creditApplication.domain.model.CreditApplication;
import com.coopcredit.creditApplication.domain.port.in.GetCreditApplicationUseCase;
import com.coopcredit.creditApplication.domain.port.out.CreditApplicationRepositoryPort;
import java.util.List;

public class GetCreditApplicationUseCaseImpl implements GetCreditApplicationUseCase {

    private final CreditApplicationRepositoryPort repositoryPort;

    public GetCreditApplicationUseCaseImpl(CreditApplicationRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public CreditApplication getById(Long id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));
    }

    @Override
    public List<CreditApplication> getAll() {
        return repositoryPort.findAll();
    }

    @Override
    public List<CreditApplication> getByAffiliateId(Long affiliateId) {
        return repositoryPort.findByAffiliateId(affiliateId);
    }
}

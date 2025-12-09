package com.coopcredit.creditApplication.domain.port.out;

import com.coopcredit.creditApplication.domain.model.CreditApplication;
import java.util.List;
import java.util.Optional;

public interface CreditApplicationRepositoryPort {
    CreditApplication save(CreditApplication creditApplication);
    Optional<CreditApplication> findById(Long id);
    List<CreditApplication> findByAffiliateId(Long affiliateId);
    List<CreditApplication> findAll(); // or with pagination/filter
}

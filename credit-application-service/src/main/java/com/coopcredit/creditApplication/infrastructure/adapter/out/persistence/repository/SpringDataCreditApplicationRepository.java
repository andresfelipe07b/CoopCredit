package com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.repository;

import com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.entity.CreditApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpringDataCreditApplicationRepository extends JpaRepository<CreditApplicationEntity, Long> {
    List<CreditApplicationEntity> findByAffiliateId(Long affiliateId);
}

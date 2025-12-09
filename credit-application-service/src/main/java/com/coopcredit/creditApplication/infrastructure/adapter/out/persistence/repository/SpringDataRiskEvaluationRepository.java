package com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.repository;

import com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.entity.RiskEvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringDataRiskEvaluationRepository extends JpaRepository<RiskEvaluationEntity, Long> {
    Optional<RiskEvaluationEntity> findByApplicationId(Long applicationId);
}

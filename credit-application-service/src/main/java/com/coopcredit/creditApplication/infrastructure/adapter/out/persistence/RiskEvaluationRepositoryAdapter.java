package com.coopcredit.creditApplication.infrastructure.adapter.out.persistence;

import com.coopcredit.creditApplication.domain.model.RiskEvaluation;
import com.coopcredit.creditApplication.domain.port.out.RiskEvaluationRepositoryPort;
import com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.entity.RiskEvaluationEntity;
import com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.mapper.RiskEvaluationMapper;
import com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.repository.SpringDataRiskEvaluationRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RiskEvaluationRepositoryAdapter implements RiskEvaluationRepositoryPort {

    private final SpringDataRiskEvaluationRepository repository;
    private final RiskEvaluationMapper mapper;

    public RiskEvaluationRepositoryAdapter(SpringDataRiskEvaluationRepository repository, RiskEvaluationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RiskEvaluation save(RiskEvaluation riskEvaluation) {
        RiskEvaluationEntity entity = mapper.toEntity(riskEvaluation);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<RiskEvaluation> findByApplicationId(Long applicationId) {
        return repository.findByApplicationId(applicationId).map(mapper::toDomain);
    }
}

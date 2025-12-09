package com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.mapper;

import com.coopcredit.creditApplication.domain.model.RiskEvaluation;
import com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.entity.CreditApplicationEntity;
import com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.entity.RiskEvaluationEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-09T11:32:49-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.17 (Ubuntu)"
)
@Component
public class RiskEvaluationMapperImpl implements RiskEvaluationMapper {

    @Override
    public RiskEvaluation toDomain(RiskEvaluationEntity entity) {
        if ( entity == null ) {
            return null;
        }

        RiskEvaluation riskEvaluation = new RiskEvaluation();

        riskEvaluation.setApplicationId( entityApplicationId( entity ) );
        riskEvaluation.setId( entity.getId() );
        riskEvaluation.setScore( entity.getScore() );
        riskEvaluation.setReason( entity.getReason() );
        riskEvaluation.setRiskLevel( entity.getRiskLevel() );
        riskEvaluation.setCreatedAt( entity.getCreatedAt() );

        return riskEvaluation;
    }

    @Override
    public RiskEvaluationEntity toEntity(RiskEvaluation domain) {
        if ( domain == null ) {
            return null;
        }

        RiskEvaluationEntity.RiskEvaluationEntityBuilder riskEvaluationEntity = RiskEvaluationEntity.builder();

        riskEvaluationEntity.application( riskEvaluationToCreditApplicationEntity( domain ) );
        riskEvaluationEntity.id( domain.getId() );
        riskEvaluationEntity.score( domain.getScore() );
        riskEvaluationEntity.reason( domain.getReason() );
        riskEvaluationEntity.riskLevel( domain.getRiskLevel() );
        riskEvaluationEntity.createdAt( domain.getCreatedAt() );

        return riskEvaluationEntity.build();
    }

    private Long entityApplicationId(RiskEvaluationEntity riskEvaluationEntity) {
        if ( riskEvaluationEntity == null ) {
            return null;
        }
        CreditApplicationEntity application = riskEvaluationEntity.getApplication();
        if ( application == null ) {
            return null;
        }
        Long id = application.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected CreditApplicationEntity riskEvaluationToCreditApplicationEntity(RiskEvaluation riskEvaluation) {
        if ( riskEvaluation == null ) {
            return null;
        }

        CreditApplicationEntity.CreditApplicationEntityBuilder creditApplicationEntity = CreditApplicationEntity.builder();

        creditApplicationEntity.id( riskEvaluation.getApplicationId() );

        return creditApplicationEntity.build();
    }
}

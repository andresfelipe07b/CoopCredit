package com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.mapper;

import com.coopcredit.creditApplication.domain.model.CreditApplication;
import com.coopcredit.creditApplication.domain.model.RiskEvaluation;
import com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.entity.AffiliateEntity;
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
public class CreditApplicationMapperImpl implements CreditApplicationMapper {

    @Override
    public CreditApplication toDomain(CreditApplicationEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CreditApplication creditApplication = new CreditApplication();

        creditApplication.setAffiliateId( entityAffiliateId( entity ) );
        creditApplication.setId( entity.getId() );
        creditApplication.setAmount( entity.getAmount() );
        creditApplication.setTerm( entity.getTerm() );
        creditApplication.setBaseRate( entity.getBaseRate() );
        creditApplication.setStatus( entity.getStatus() );
        creditApplication.setCreatedAt( entity.getCreatedAt() );
        creditApplication.setEvaluation( riskEvaluationEntityToRiskEvaluation( entity.getEvaluation() ) );

        return creditApplication;
    }

    @Override
    public CreditApplicationEntity toEntity(CreditApplication domain) {
        if ( domain == null ) {
            return null;
        }

        CreditApplicationEntity.CreditApplicationEntityBuilder creditApplicationEntity = CreditApplicationEntity.builder();

        creditApplicationEntity.affiliate( creditApplicationToAffiliateEntity( domain ) );
        creditApplicationEntity.id( domain.getId() );
        creditApplicationEntity.amount( domain.getAmount() );
        creditApplicationEntity.term( domain.getTerm() );
        creditApplicationEntity.baseRate( domain.getBaseRate() );
        creditApplicationEntity.status( domain.getStatus() );
        creditApplicationEntity.createdAt( domain.getCreatedAt() );
        creditApplicationEntity.evaluation( riskEvaluationToRiskEvaluationEntity( domain.getEvaluation() ) );

        return creditApplicationEntity.build();
    }

    private Long entityAffiliateId(CreditApplicationEntity creditApplicationEntity) {
        if ( creditApplicationEntity == null ) {
            return null;
        }
        AffiliateEntity affiliate = creditApplicationEntity.getAffiliate();
        if ( affiliate == null ) {
            return null;
        }
        Long id = affiliate.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected RiskEvaluation riskEvaluationEntityToRiskEvaluation(RiskEvaluationEntity riskEvaluationEntity) {
        if ( riskEvaluationEntity == null ) {
            return null;
        }

        RiskEvaluation riskEvaluation = new RiskEvaluation();

        riskEvaluation.setId( riskEvaluationEntity.getId() );
        riskEvaluation.setScore( riskEvaluationEntity.getScore() );
        riskEvaluation.setReason( riskEvaluationEntity.getReason() );
        riskEvaluation.setRiskLevel( riskEvaluationEntity.getRiskLevel() );
        riskEvaluation.setCreatedAt( riskEvaluationEntity.getCreatedAt() );

        return riskEvaluation;
    }

    protected AffiliateEntity creditApplicationToAffiliateEntity(CreditApplication creditApplication) {
        if ( creditApplication == null ) {
            return null;
        }

        AffiliateEntity.AffiliateEntityBuilder affiliateEntity = AffiliateEntity.builder();

        affiliateEntity.id( creditApplication.getAffiliateId() );

        return affiliateEntity.build();
    }

    protected RiskEvaluationEntity riskEvaluationToRiskEvaluationEntity(RiskEvaluation riskEvaluation) {
        if ( riskEvaluation == null ) {
            return null;
        }

        RiskEvaluationEntity.RiskEvaluationEntityBuilder riskEvaluationEntity = RiskEvaluationEntity.builder();

        riskEvaluationEntity.id( riskEvaluation.getId() );
        riskEvaluationEntity.score( riskEvaluation.getScore() );
        riskEvaluationEntity.reason( riskEvaluation.getReason() );
        riskEvaluationEntity.riskLevel( riskEvaluation.getRiskLevel() );
        riskEvaluationEntity.createdAt( riskEvaluation.getCreatedAt() );

        return riskEvaluationEntity.build();
    }
}

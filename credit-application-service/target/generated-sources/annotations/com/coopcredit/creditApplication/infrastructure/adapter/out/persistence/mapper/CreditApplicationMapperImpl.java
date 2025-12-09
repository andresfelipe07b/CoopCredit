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
    date = "2025-12-09T08:46:25-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
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
        creditApplicationEntity.amount( domain.getAmount() );
        creditApplicationEntity.baseRate( domain.getBaseRate() );
        creditApplicationEntity.createdAt( domain.getCreatedAt() );
        creditApplicationEntity.evaluation( riskEvaluationToRiskEvaluationEntity( domain.getEvaluation() ) );
        creditApplicationEntity.id( domain.getId() );
        creditApplicationEntity.status( domain.getStatus() );
        creditApplicationEntity.term( domain.getTerm() );

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

        riskEvaluationEntity.createdAt( riskEvaluation.getCreatedAt() );
        riskEvaluationEntity.id( riskEvaluation.getId() );
        riskEvaluationEntity.reason( riskEvaluation.getReason() );
        riskEvaluationEntity.riskLevel( riskEvaluation.getRiskLevel() );
        riskEvaluationEntity.score( riskEvaluation.getScore() );

        return riskEvaluationEntity.build();
    }
}

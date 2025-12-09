package com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.mapper;

import com.coopcredit.creditApplication.domain.model.RiskEvaluation;
import com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.entity.RiskEvaluationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RiskEvaluationMapper {
    @Mapping(source = "application.id", target = "applicationId")
    RiskEvaluation toDomain(RiskEvaluationEntity entity);

    @Mapping(source = "applicationId", target = "application.id")
    RiskEvaluationEntity toEntity(RiskEvaluation domain);
}

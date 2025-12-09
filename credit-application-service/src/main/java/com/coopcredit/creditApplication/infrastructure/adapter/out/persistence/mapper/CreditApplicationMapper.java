package com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.mapper;

import com.coopcredit.creditApplication.domain.model.CreditApplication;
import com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.entity.CreditApplicationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreditApplicationMapper {
    @Mapping(source = "affiliate.id", target = "affiliateId")
    CreditApplication toDomain(CreditApplicationEntity entity);

    @Mapping(source = "affiliateId", target = "affiliate.id")
    CreditApplicationEntity toEntity(CreditApplication domain);
}

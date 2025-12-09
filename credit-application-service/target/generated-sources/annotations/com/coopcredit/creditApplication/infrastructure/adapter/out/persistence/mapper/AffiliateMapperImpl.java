package com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.mapper;

import com.coopcredit.creditApplication.domain.model.Affiliate;
import com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.entity.AffiliateEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-09T08:46:25-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class AffiliateMapperImpl implements AffiliateMapper {

    @Override
    public Affiliate toDomain(AffiliateEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Affiliate affiliate = new Affiliate();

        affiliate.setId( entity.getId() );
        affiliate.setName( entity.getName() );
        affiliate.setEmail( entity.getEmail() );
        affiliate.setDocument( entity.getDocument() );
        affiliate.setSalary( entity.getSalary() );
        affiliate.setAddress( entity.getAddress() );
        affiliate.setStartDate( entity.getStartDate() );
        affiliate.setActive( entity.isActive() );

        return affiliate;
    }

    @Override
    public AffiliateEntity toEntity(Affiliate domain) {
        if ( domain == null ) {
            return null;
        }

        AffiliateEntity.AffiliateEntityBuilder affiliateEntity = AffiliateEntity.builder();

        affiliateEntity.active( domain.isActive() );
        affiliateEntity.address( domain.getAddress() );
        affiliateEntity.document( domain.getDocument() );
        affiliateEntity.email( domain.getEmail() );
        affiliateEntity.id( domain.getId() );
        affiliateEntity.name( domain.getName() );
        affiliateEntity.salary( domain.getSalary() );
        affiliateEntity.startDate( domain.getStartDate() );

        return affiliateEntity.build();
    }
}

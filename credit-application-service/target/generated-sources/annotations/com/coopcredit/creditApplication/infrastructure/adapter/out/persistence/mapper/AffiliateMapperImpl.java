package com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.mapper;

import com.coopcredit.creditApplication.domain.model.Affiliate;
import com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.entity.AffiliateEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-09T11:32:49-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.17 (Ubuntu)"
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

        affiliateEntity.id( domain.getId() );
        affiliateEntity.name( domain.getName() );
        affiliateEntity.email( domain.getEmail() );
        affiliateEntity.document( domain.getDocument() );
        affiliateEntity.salary( domain.getSalary() );
        affiliateEntity.address( domain.getAddress() );
        affiliateEntity.startDate( domain.getStartDate() );
        affiliateEntity.active( domain.isActive() );

        return affiliateEntity.build();
    }
}

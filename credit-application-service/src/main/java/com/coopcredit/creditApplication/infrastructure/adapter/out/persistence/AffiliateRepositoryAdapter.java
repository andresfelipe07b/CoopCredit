package com.coopcredit.creditApplication.infrastructure.adapter.out.persistence;

import com.coopcredit.creditApplication.domain.model.Affiliate;
import com.coopcredit.creditApplication.domain.port.out.AffiliateRepositoryPort;
import com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.entity.AffiliateEntity;
import com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.mapper.AffiliateMapper;
import com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.repository.SpringDataAffiliateRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AffiliateRepositoryAdapter implements AffiliateRepositoryPort {

    private final SpringDataAffiliateRepository repository;
    private final AffiliateMapper mapper;

    public AffiliateRepositoryAdapter(SpringDataAffiliateRepository repository, AffiliateMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Affiliate save(Affiliate affiliate) {
        AffiliateEntity entity = mapper.toEntity(affiliate);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Affiliate> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Affiliate> findByDocument(String document) {
        return repository.findByDocument(document).map(mapper::toDomain);
    }

    @Override
    public boolean existsByDocument(String document) {
        return repository.existsByDocument(document);
    }
}

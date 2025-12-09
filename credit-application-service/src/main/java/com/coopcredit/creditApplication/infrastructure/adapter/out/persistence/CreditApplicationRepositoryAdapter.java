package com.coopcredit.creditApplication.infrastructure.adapter.out.persistence;

import com.coopcredit.creditApplication.domain.model.CreditApplication;
import com.coopcredit.creditApplication.domain.port.out.CreditApplicationRepositoryPort;
import com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.entity.CreditApplicationEntity;
import com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.mapper.CreditApplicationMapper;
import com.coopcredit.creditApplication.infrastructure.adapter.out.persistence.repository.SpringDataCreditApplicationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CreditApplicationRepositoryAdapter implements CreditApplicationRepositoryPort {

    private final SpringDataCreditApplicationRepository repository;
    private final CreditApplicationMapper mapper;

    public CreditApplicationRepositoryAdapter(SpringDataCreditApplicationRepository repository,
            CreditApplicationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CreditApplication save(CreditApplication creditApplication) {
        CreditApplicationEntity entity = mapper.toEntity(creditApplication);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<CreditApplication> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<CreditApplication> findByAffiliateId(Long affiliateId) {
        return repository.findByAffiliateId(affiliateId)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditApplication> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }
}

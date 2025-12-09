package com.coopcredit.creditApplication.infrastructure.adapter.in.web.controller;

import com.coopcredit.creditApplication.domain.model.CreditApplication;
import com.coopcredit.creditApplication.domain.port.in.CreateCreditApplicationUseCase;
import com.coopcredit.creditApplication.domain.port.in.EvaluateCreditApplicationUseCase;
import com.coopcredit.creditApplication.infrastructure.adapter.in.web.dto.CreditApplicationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/applications")
public class CreditApplicationController {

    private final CreateCreditApplicationUseCase createUseCase;
    private final EvaluateCreditApplicationUseCase evaluateUseCase;
    private final com.coopcredit.creditApplication.domain.port.in.GetCreditApplicationUseCase getUseCase;
    private final com.coopcredit.creditApplication.domain.port.out.AffiliateRepositoryPort affiliateRepository;

    public CreditApplicationController(CreateCreditApplicationUseCase createUseCase,
            EvaluateCreditApplicationUseCase evaluateUseCase,
            com.coopcredit.creditApplication.domain.port.in.GetCreditApplicationUseCase getUseCase,
            com.coopcredit.creditApplication.domain.port.out.AffiliateRepositoryPort affiliateRepository) {
        this.createUseCase = createUseCase;
        this.evaluateUseCase = evaluateUseCase;
        this.getUseCase = getUseCase;
        this.affiliateRepository = affiliateRepository;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('AFILIADO', 'ADMIN')")
    public ResponseEntity<CreditApplication> create(@RequestBody CreditApplicationRequest request) {
        // Validate ownership if necessary

        CreditApplication domain = new CreditApplication();
        domain.setAffiliateId(request.getAffiliateId());
        domain.setAmount(request.getAmount());
        domain.setTerm(request.getTerm());

        CreditApplication saved = createUseCase.create(domain);

        return ResponseEntity.created(URI.create("/applications/" + saved.getId())).body(saved);
    }

    @PostMapping("/{id}/evaluate")
    @PreAuthorize("hasAnyRole('ANALISTA', 'ADMIN')")
    public ResponseEntity<Void> evaluate(@PathVariable Long id) {
        evaluateUseCase.evaluate(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<java.util.List<CreditApplication>> list() {
        org.springframework.security.core.Authentication auth = org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication();
        boolean isAnalyst = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ANALISTA") || a.getAuthority().equals("ROLE_ADMIN"));

        if (isAnalyst) {
            return ResponseEntity.ok(getUseCase.getAll());
        } else {
            String document = auth.getName();
            return affiliateRepository.findByDocument(document)
                    .map(affiliate -> ResponseEntity.ok(getUseCase.getByAffiliateId(affiliate.getId())))
                    .orElse(ResponseEntity.ok(java.util.List.of()));
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CreditApplication> get(@PathVariable Long id) {
        CreditApplication app = getUseCase.getById(id);

        org.springframework.security.core.Authentication auth = org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication();
        boolean isAnalyst = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ANALISTA") || a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAnalyst) {
            String document = auth.getName();
            Long affiliateId = affiliateRepository.findByDocument(document)
                    .map(com.coopcredit.creditApplication.domain.model.Affiliate::getId).orElse(-1L);
            if (!app.getAffiliateId().equals(affiliateId)) {
                return ResponseEntity.status(403).build();
            }
        }

        return ResponseEntity.ok(app);
    }
}

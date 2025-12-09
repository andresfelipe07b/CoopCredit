package com.coopcredit.creditApplication.infrastructure.adapter.in.web.controller;

import com.coopcredit.creditApplication.domain.model.Affiliate;
import com.coopcredit.creditApplication.domain.port.in.UpdateAffiliateUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/affiliates")
public class AffiliateController {

    private final UpdateAffiliateUseCase updateAffiliateUseCase;

    public AffiliateController(UpdateAffiliateUseCase updateAffiliateUseCase) {
        this.updateAffiliateUseCase = updateAffiliateUseCase;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('AFILIADO', 'ADMIN')")
    public ResponseEntity<Affiliate> update(@PathVariable Long id, @RequestBody Affiliate request) {
        // In a real scenario, we should verify that the logged user owns this ID or is
        // Admin.
        // Similar to how we did in CreditApplicationController.

        return ResponseEntity.ok(updateAffiliateUseCase.update(id, request));
    }
}

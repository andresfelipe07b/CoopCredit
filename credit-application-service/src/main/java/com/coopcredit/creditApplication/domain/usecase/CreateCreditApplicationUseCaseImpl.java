package com.coopcredit.creditApplication.domain.usecase;

import com.coopcredit.creditApplication.domain.model.Affiliate;
import com.coopcredit.creditApplication.domain.model.ApplicationStatus;
import com.coopcredit.creditApplication.domain.model.CreditApplication;
import com.coopcredit.creditApplication.domain.port.in.CreateCreditApplicationUseCase;
import com.coopcredit.creditApplication.domain.port.out.AffiliateRepositoryPort;
import com.coopcredit.creditApplication.domain.port.out.CreditApplicationRepositoryPort;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CreateCreditApplicationUseCaseImpl implements CreateCreditApplicationUseCase {

    private final CreditApplicationRepositoryPort creditApplicationRepositoryPort;
    private final AffiliateRepositoryPort affiliateRepositoryPort;
    private final com.coopcredit.creditApplication.domain.port.out.RiskEvaluationExternalPort riskEvaluationExternalPort;

    public CreateCreditApplicationUseCaseImpl(CreditApplicationRepositoryPort creditApplicationRepositoryPort,
            AffiliateRepositoryPort affiliateRepositoryPort,
            com.coopcredit.creditApplication.domain.port.out.RiskEvaluationExternalPort riskEvaluationExternalPort) {
        this.creditApplicationRepositoryPort = creditApplicationRepositoryPort;
        this.affiliateRepositoryPort = affiliateRepositoryPort;
        this.riskEvaluationExternalPort = riskEvaluationExternalPort;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public CreditApplication create(CreditApplication creditApplication) {

        Affiliate affiliate = affiliateRepositoryPort.findById(creditApplication.getAffiliateId())
                .orElseThrow(() -> new IllegalArgumentException("Affiliate not found"));

        if (!affiliate.isActive()) {
            throw new IllegalArgumentException("Affiliate is not active");
        }

        long monthsSinceStart = ChronoUnit.MONTHS.between(affiliate.getStartDate(), java.time.LocalDate.now());
        if (monthsSinceStart < 6) {
            throw new IllegalArgumentException("Affiliate has less than 6 months of seniority");
        }

        // Policy: Max Amount (10x Salary)
        if (creditApplication.getAmount() > affiliate.getSalary() * 10) {
            throw new IllegalArgumentException("Amount exceeds maximum allowed (10x Salary)");
        }

        if (creditApplication.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        // Set status to PENDING for independent evaluation (Step 5 of requirements)
        creditApplication.setStatus(ApplicationStatus.PENDING);
        creditApplication.setCreatedAt(LocalDateTime.now());
        creditApplication.setEvaluation(null); // No evaluation yet

        return creditApplicationRepositoryPort.save(creditApplication);
    }
}

package com.coopcredit.creditApplication.domain.usecase;

import com.coopcredit.creditApplication.domain.model.Affiliate;
import com.coopcredit.creditApplication.domain.model.ApplicationStatus;
import com.coopcredit.creditApplication.domain.model.CreditApplication;
import com.coopcredit.creditApplication.domain.model.RiskEvaluation;
import com.coopcredit.creditApplication.domain.port.in.EvaluateCreditApplicationUseCase;
import com.coopcredit.creditApplication.domain.port.out.AffiliateRepositoryPort;
import com.coopcredit.creditApplication.domain.port.out.CreditApplicationRepositoryPort;
import com.coopcredit.creditApplication.domain.port.out.RiskEvaluationExternalPort;
import com.coopcredit.creditApplication.domain.port.out.RiskEvaluationRepositoryPort;

import java.time.LocalDateTime;

public class EvaluateCreditApplicationUseCaseImpl implements EvaluateCreditApplicationUseCase {

    private final CreditApplicationRepositoryPort creditApplicationRepositoryPort;
    private final RiskEvaluationExternalPort riskEvaluationExternalPort;
    private final RiskEvaluationRepositoryPort riskEvaluationRepositoryPort;
    private final AffiliateRepositoryPort affiliateRepositoryPort;

    public EvaluateCreditApplicationUseCaseImpl(CreditApplicationRepositoryPort creditApplicationRepositoryPort,
            RiskEvaluationExternalPort riskEvaluationExternalPort,
            RiskEvaluationRepositoryPort riskEvaluationRepositoryPort,
            AffiliateRepositoryPort affiliateRepositoryPort) {
        this.creditApplicationRepositoryPort = creditApplicationRepositoryPort;
        this.riskEvaluationExternalPort = riskEvaluationExternalPort;
        this.riskEvaluationRepositoryPort = riskEvaluationRepositoryPort;
        this.affiliateRepositoryPort = affiliateRepositoryPort;
    }

    @Override
    public void evaluate(Long creditApplicationId) {
        CreditApplication application = creditApplicationRepositoryPort.findById(creditApplicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        if (application.getStatus() != ApplicationStatus.PENDING) {
            // Requirement: Analyst evaluates PENDING applications
            throw new IllegalStateException("Application is not pending");
        }

        Affiliate affiliate = affiliateRepositoryPort.findById(application.getAffiliateId())
                .orElseThrow(() -> new IllegalArgumentException("Affiliate not found"));

        // Call External Risk Service
        RiskEvaluation evaluation = riskEvaluationExternalPort.evaluate(affiliate.getDocument(),
                application.getAmount(), application.getTerm());

        evaluation.setApplicationId(creditApplicationId);
        evaluation.setCreatedAt(LocalDateTime.now());

        // Decision Logic (Consistent with Create Use Case)
        if ("ALTO RIESGO".equals(evaluation.getRiskLevel())) {
            application.setStatus(ApplicationStatus.REJECTED);
            if (evaluation.getReason() == null)
                evaluation.setReason("Rejected due to High Risk level");
        } else {
            double monthlyPayment = application.getAmount() / application.getTerm();
            double maxCapacity = affiliate.getSalary() * 0.30;

            if (monthlyPayment > maxCapacity) {
                application.setStatus(ApplicationStatus.REJECTED);
                if (evaluation.getReason() == null)
                    evaluation.setReason("Rejected: Monthly payment exceeds 30% of salary");
            } else {
                application.setStatus(ApplicationStatus.APPROVED);
                if (evaluation.getReason() == null)
                    evaluation.setReason("Key Approval: Good standing");
            }
        }

        riskEvaluationRepositoryPort.save(evaluation);
        creditApplicationRepositoryPort.save(application);
    }
}

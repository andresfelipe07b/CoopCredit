package com.coopcredit.creditApplication.infrastructure.config;

import com.coopcredit.creditApplication.domain.port.in.CreateCreditApplicationUseCase;
import com.coopcredit.creditApplication.domain.port.in.EvaluateCreditApplicationUseCase;
import com.coopcredit.creditApplication.domain.port.in.GetCreditApplicationUseCase;
import com.coopcredit.creditApplication.domain.port.in.RegisterAffiliateUseCase;
import com.coopcredit.creditApplication.domain.port.out.AffiliateRepositoryPort;
import com.coopcredit.creditApplication.domain.port.out.CreditApplicationRepositoryPort;
import com.coopcredit.creditApplication.domain.port.out.RiskEvaluationExternalPort;
import com.coopcredit.creditApplication.domain.port.out.RiskEvaluationRepositoryPort;
import com.coopcredit.creditApplication.domain.usecase.CreateCreditApplicationUseCaseImpl;
import com.coopcredit.creditApplication.domain.usecase.EvaluateCreditApplicationUseCaseImpl;
import com.coopcredit.creditApplication.domain.usecase.GetCreditApplicationUseCaseImpl;
import com.coopcredit.creditApplication.domain.usecase.RegisterAffiliateUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainBeanConfiguration {

    @Bean
    public RegisterAffiliateUseCase registerAffiliateUseCase(AffiliateRepositoryPort affiliateRepositoryPort) {
        return new RegisterAffiliateUseCaseImpl(affiliateRepositoryPort);
    }

    @Bean
    public CreateCreditApplicationUseCase createCreditApplicationUseCase(
            CreditApplicationRepositoryPort creditApplicationRepositoryPort,
            AffiliateRepositoryPort affiliateRepositoryPort,
            RiskEvaluationExternalPort riskEvaluationExternalPort) {
        return new CreateCreditApplicationUseCaseImpl(creditApplicationRepositoryPort, affiliateRepositoryPort,
                riskEvaluationExternalPort);
    }

    @Bean
    public EvaluateCreditApplicationUseCase evaluateCreditApplicationUseCase(
            CreditApplicationRepositoryPort creditApplicationRepositoryPort,
            RiskEvaluationExternalPort riskEvaluationExternalPort,
            RiskEvaluationRepositoryPort riskEvaluationRepositoryPort,
            AffiliateRepositoryPort affiliateRepositoryPort) {
        return new EvaluateCreditApplicationUseCaseImpl(creditApplicationRepositoryPort, riskEvaluationExternalPort,
                riskEvaluationRepositoryPort, affiliateRepositoryPort);
    }

    @Bean
    public GetCreditApplicationUseCase getCreditApplicationUseCase(
            CreditApplicationRepositoryPort creditApplicationRepositoryPort) {
        return new GetCreditApplicationUseCaseImpl(creditApplicationRepositoryPort);
    }
}

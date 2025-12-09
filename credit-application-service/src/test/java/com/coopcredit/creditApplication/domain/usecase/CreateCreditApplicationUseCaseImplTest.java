package com.coopcredit.creditApplication.domain.usecase;

import com.coopcredit.creditApplication.domain.model.Affiliate;
import com.coopcredit.creditApplication.domain.model.CreditApplication;
import com.coopcredit.creditApplication.domain.port.out.AffiliateRepositoryPort;
import com.coopcredit.creditApplication.domain.port.out.CreditApplicationRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCreditApplicationUseCaseImplTest {

    @Mock
    private CreditApplicationRepositoryPort creditRepo;
    @Mock
    private AffiliateRepositoryPort affiliateRepo;
    @Mock
    private com.coopcredit.creditApplication.domain.port.out.RiskEvaluationExternalPort riskRepo;

    @InjectMocks
    private CreateCreditApplicationUseCaseImpl useCase;

    @Test
    void create_ShouldSucceed_WhenRulesMet() {
        Affiliate affiliate = new Affiliate(1L, "Test", "mail", "123", 1000.0, "Addr", LocalDate.now().minusMonths(7),
                true);
        CreditApplication app = new CreditApplication();
        app.setAffiliateId(1L);
        app.setAmount(5000.0);
        app.setTerm(12);

        com.coopcredit.creditApplication.domain.model.RiskEvaluation riskEval = new com.coopcredit.creditApplication.domain.model.RiskEvaluation(
                null, null, 800, "OK", "BAJO RIESGO", java.time.LocalDateTime.now());

        when(affiliateRepo.findById(1L)).thenReturn(Optional.of(affiliate));
        when(riskRepo.evaluate("123", 5000.0, 12)).thenReturn(riskEval);
        when(creditRepo.save(any())).thenAnswer(i -> i.getArguments()[0]);

        CreditApplication result = useCase.create(app);
        assertNotNull(result);
        verify(creditRepo).save(app);
    }

    @Test
    void create_ShouldThrow_WhenAffiliateInactive() {
        Affiliate affiliate = new Affiliate(1L, "Test", "mail", "123", 1000.0, "Addr", LocalDate.now().minusMonths(7),
                false);
        CreditApplication app = new CreditApplication();
        app.setAffiliateId(1L);
        when(affiliateRepo.findById(1L)).thenReturn(Optional.of(affiliate));

        assertThrows(IllegalArgumentException.class, () -> useCase.create(app));
    }
}

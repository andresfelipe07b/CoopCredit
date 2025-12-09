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

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        when(affiliateRepo.findById(1L)).thenReturn(Optional.of(affiliate));
        // Remove riskRepo stubbing as it's no longer called in Create
        when(creditRepo.save(any())).thenAnswer(i -> i.getArguments()[0]);

        CreditApplication result = useCase.create(app);
        assertNotNull(result);
        assertEquals(com.coopcredit.creditApplication.domain.model.ApplicationStatus.PENDING, result.getStatus());
        verify(creditRepo).save(app);
    }

    @Test
    void create_ShouldThrow_WhenAmountExceeds10xSalary() {
        Affiliate affiliate = new Affiliate(1L, "Test", "mail", "123", 1000.0, "Addr", LocalDate.now().minusMonths(7),
                true);
        CreditApplication app = new CreditApplication();
        app.setAffiliateId(1L);
        app.setAmount(15000.0); // 15x salary

        when(affiliateRepo.findById(1L)).thenReturn(Optional.of(affiliate));

        assertThrows(IllegalArgumentException.class, () -> useCase.create(app));
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

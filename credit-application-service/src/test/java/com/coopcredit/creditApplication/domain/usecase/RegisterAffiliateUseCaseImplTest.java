package com.coopcredit.creditApplication.domain.usecase;

import com.coopcredit.creditApplication.domain.model.Affiliate;
import com.coopcredit.creditApplication.domain.port.out.AffiliateRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterAffiliateUseCaseImplTest {

    @Mock
    private AffiliateRepositoryPort affiliateRepositoryPort;

    @InjectMocks
    private RegisterAffiliateUseCaseImpl useCase;

    @Test
    void register_ShouldRegister_WhenValid() {
        Affiliate affiliate = new Affiliate(null, "Test", "test@mail.com", "123", 1000.0, "Address", LocalDate.now(),
                true);
        when(affiliateRepositoryPort.existsByDocument("123")).thenReturn(false);
        when(affiliateRepositoryPort.save(any(Affiliate.class))).thenAnswer(i -> i.getArguments()[0]);

        Affiliate result = useCase.register(affiliate);

        assertNotNull(result);
        verify(affiliateRepositoryPort).save(affiliate);
    }

    @Test
    void register_ShouldThrow_WhenDocumentExists() {
        Affiliate affiliate = new Affiliate(null, "Test", "test@mail.com", "123", 1000.0, "Address", LocalDate.now(),
                true);
        when(affiliateRepositoryPort.existsByDocument("123")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> useCase.register(affiliate));
    }
}

package com.coopcredit.creditApplication.infrastructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditApplicationRequest {
    @NotNull(message = "Affiliate ID is required")
    private Long affiliateId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;

    @NotNull(message = "Term is required")
    @Positive(message = "Term must be positive")
    private Integer term;
}

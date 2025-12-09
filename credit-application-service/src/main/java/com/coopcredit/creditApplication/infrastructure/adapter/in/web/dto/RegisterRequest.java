package com.coopcredit.creditApplication.infrastructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String document;
    private Double salary;
    private String address;
    private LocalDate startDate;
    private String password;
    private String role; // ROLE_AFILIADO, etc.
}

package com.coopcredit.creditApplication.infrastructure.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username; // can be email or document

    private String password;

    private String roles; // Comma separated: ROLE_AFILIADO, ROLE_ANALISTA, ROLE_ADMIN
}

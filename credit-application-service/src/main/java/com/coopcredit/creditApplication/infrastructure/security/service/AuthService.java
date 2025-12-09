package com.coopcredit.creditApplication.infrastructure.security.service;

import com.coopcredit.creditApplication.domain.model.Affiliate;
import com.coopcredit.creditApplication.domain.port.in.RegisterAffiliateUseCase;
import com.coopcredit.creditApplication.infrastructure.adapter.in.web.dto.AuthenticationRequest;
import com.coopcredit.creditApplication.infrastructure.adapter.in.web.dto.AuthenticationResponse;
import com.coopcredit.creditApplication.infrastructure.adapter.in.web.dto.RegisterRequest;
import com.coopcredit.creditApplication.infrastructure.security.entity.UserEntity;
import com.coopcredit.creditApplication.infrastructure.security.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RegisterAffiliateUseCase registerAffiliateUseCase;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthService(UserRepository userRepository,
            RegisterAffiliateUseCase registerAffiliateUseCase,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager,
            UserDetailsServiceImpl userDetailsService) {
        this.userRepository = userRepository;
        this.registerAffiliateUseCase = registerAffiliateUseCase;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        // Create User
        var user = UserEntity.builder()
                .username(request.getDocument())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(request.getRole() != null ? request.getRole() : "ROLE_AFILIADO")
                .build();
        userRepository.save(user);

        // Create Affiliate Domain Object
        Affiliate affiliate = new Affiliate(
                null,
                request.getName(),
                request.getEmail(),
                request.getDocument(),
                request.getSalary(),
                request.getAddress(),
                request.getStartDate(),
                true);
        registerAffiliateUseCase.register(affiliate);

        // Generate Token
        var userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        var jwtToken = jwtService.generateToken(userDetails);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));
        var userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        var jwtToken = jwtService.generateToken(userDetails);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}

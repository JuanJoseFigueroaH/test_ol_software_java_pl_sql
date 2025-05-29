package com.testsoftware.merchant.establishment.auth.application.service;

import com.testsoftware.merchant.establishment.auth.domain.model.Auth;
import com.testsoftware.merchant.establishment.auth.domain.port.UserRepository;
import com.testsoftware.merchant.establishment.auth.infrastructure.dto.LoginRequest;
import com.testsoftware.merchant.establishment.auth.infrastructure.dto.LoginResponse;
import com.testsoftware.merchant.establishment.auth.infrastructure.exception.UserNotFoundException;
import com.testsoftware.merchant.establishment.auth.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        Auth auth = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(
                        "User not found (This should not occur after successful authentication)"));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtUtil.generateToken(userDetails, auth.getRole().name(), auth.getId());
        System.out.println(token);
        return new LoginResponse(token);
    }
}
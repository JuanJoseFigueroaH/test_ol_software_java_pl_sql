package com.testsoftware.merchant.establishment.auth.application.service;

import com.testsoftware.merchant.establishment.auth.domain.model.Auth;
import com.testsoftware.merchant.establishment.auth.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Auth auth = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " + email));

        return new User(auth.getEmail(), auth.getPassword(), Collections.singletonList(
                new org.springframework.security.core.authority.SimpleGrantedAuthority(
                        "ROLE_" + auth.getRole().name())));
    }
}
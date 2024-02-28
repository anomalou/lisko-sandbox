package com.lisko.service.impl;

import com.lisko.config.ApplicationConfig;
import com.lisko.dto.request.SignInRequest;
import com.lisko.dto.request.SignUpRequest;
import com.lisko.dto.response.JwtResponse;
import com.lisko.entity.Role;
import com.lisko.entity.User;
import com.lisko.exception.AuthorizationException;
import com.lisko.exception.RegistrationException;
import com.lisko.exception.RoleNotFoundException;
import com.lisko.repository.RoleRepository;
import com.lisko.repository.UserRepository;
import com.lisko.security.jwt.JwtUtil;
import com.lisko.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/2/24
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtUtil util;
    private final UserDetailsService detailsService;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final ApplicationConfig config;

    @Override
    public JwtResponse signIn(SignInRequest request) {
        UserDetails details = detailsService.loadUserByUsername(request.getUsername());

        if (encoder.matches(request.getPassword(), details.getPassword())) {
            String token = util.generate(details.getUsername());

            log.info("User {} have been generated token", request.getUsername());

            return new JwtResponse(token);
        } else {
            throw new AuthorizationException(details.getUsername());
        }
    }

    @Override
    public JwtResponse signUp(SignUpRequest request) {
        try {
            if (!userRepository.findByUsername(request.getUsername()).isPresent()) {
                throw new RegistrationException();
            }

            String encodedPassword = encoder.encode(request.getPassword());

            Role role = roleRepository.findByName(config.getBaseRegRole()).orElseThrow(() -> new RoleNotFoundException(config.getBaseRegRole()));

            User user = User.builder()
                    .username(request.getUsername())
                    .password(encodedPassword)
                    .regDate(LocalDateTime.now())
                    .role(role)
                    .build();

            userRepository.save(user);

            SignInRequest signInRequest = SignInRequest.builder()
                    .username(request.getUsername())
                    .password(request.getPassword())
                    .build();

            return signIn(signInRequest);
        } catch (Exception e) {
            log.error("Registration failed. Exception: {}", e.getMessage());
            throw e;
        }
    }
}

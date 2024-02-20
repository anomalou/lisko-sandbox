package com.lisko.controller;

import com.lisko.dto.request.SignInRequest;
import com.lisko.dto.request.SignUpRequest;
import com.lisko.dto.response.JwtResponse;
import com.lisko.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/2/24
 */

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/login")
    public JwtResponse signIn(@RequestBody SignInRequest request) {
        return service.signIn(request);
    }

    @PostMapping("/reg")
    public JwtResponse signUp(@RequestBody SignUpRequest request) {
        return service.signUp(request);
    }

}

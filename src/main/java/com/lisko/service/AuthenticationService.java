package com.lisko.service;

import com.lisko.dto.request.SignInRequest;
import com.lisko.dto.request.SignUpRequest;
import com.lisko.dto.response.JwtResponse;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/2/24
 */
public interface AuthenticationService {
    JwtResponse signIn(SignInRequest request);
    JwtResponse signUp(SignUpRequest request);
}

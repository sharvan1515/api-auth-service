package com.security.auth_service.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.auth_service.dto.LoginRequest;
import com.security.auth_service.dto.LoginResponse;
import com.security.auth_service.dto.RegisterRequest;
import com.security.auth_service.dto.SuccessApiResponse;
import com.security.auth_service.entity.AuthUser;
import com.security.auth_service.exception.UsernameFound;
import com.security.auth_service.repository.AuthUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthUserRepository authUserRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public SuccessApiResponse registerUser(RegisterRequest registerRequest) {

        authUserRepository.findByUsername(registerRequest.getUsername())
                .ifPresent(user -> {
                    throw new UsernameFound("Username " + registerRequest.getUsername() + " is already taken");
                });

        AuthUser user = new AuthUser();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole("USER");
        authUserRepository.save(user);

        SuccessApiResponse response = new SuccessApiResponse();
        response.setMessage("User registered successfully");
        return response;
    }

    public LoginResponse loginUser(LoginRequest loginRequest) {
        

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        System.out.println("-----------");
        System.out.println(authentication);

        return new LoginResponse("Login Success !");

    }

}

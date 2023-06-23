package com.example.testloginregister.auth.controller;

import com.example.testloginregister.auth.AuthenticateRequest;
import com.example.testloginregister.auth.AuthenticateService;
import com.example.testloginregister.auth.AuthenticationResponse;
import com.example.testloginregister.auth.RegisterRequest;
import com.example.testloginregister.repository.AppUserRepository;
import com.example.testloginregister.user.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticateService authenticateService;
    private final AppUserRepository appUserRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        if (appUserRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(new AuthenticationResponse("User with that email already exists"));
        }

        return ResponseEntity.ok(authenticateService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticateRequest request
    ) {
        return ResponseEntity.ok(authenticateService.authenticate(request));
    }
}

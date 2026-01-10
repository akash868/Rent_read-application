package com.crio.rent_read.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.crio.rent_read.dto.AuthRequest;
import com.crio.rent_read.dto.AuthResponse;
import com.crio.rent_read.entity.User;
import com.crio.rent_read.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@Valid @RequestBody User request) {
        User saved = authService.register(request);
        logger.info("Registered user: {}", saved.getEmail());
        return ResponseEntity.status(201).body(new AuthResponse(saved));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest body) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        User user = authService.findByEmail(body.getEmail()).orElseThrow();
        return ResponseEntity.ok(new AuthResponse(user));
    }
}

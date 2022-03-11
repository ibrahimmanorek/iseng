package com.backend.tempatusaha.controller;

import com.backend.tempatusaha.dto.request.LoginRequest;
import com.backend.tempatusaha.dto.request.RefreshTokenRequest;
import com.backend.tempatusaha.dto.request.SignUpRequest;
import com.backend.tempatusaha.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok().body(authService.save(signUpRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok().body(authService.login(loginRequest));
    }

    @PostMapping("/refresh/token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok().body(authService.refreshToken(refreshTokenRequest));
    }
}

package com.backend.tempatusaha.controller;

import com.backend.tempatusaha.dto.request.*;
import com.backend.tempatusaha.service.auth.AuthService;
import com.backend.tempatusaha.utils.AesUtils;
import com.backend.tempatusaha.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok().body(authService.save(signUpRequest));
    }

    @PostMapping("/resend/otp")
    public ResponseEntity<?> resendOtp(@RequestParam("email") String email) {
        return ResponseEntity.ok().body(authService.resendOtp(email));
    }

    @GetMapping(Constant.Path.FORGOT_PASSWORD)
    public ResponseEntity<?> getForgotPassword(@RequestParam String e) {
        String decrypt = AesUtils.decrypt(e);
        log.info(">>> {}", decrypt);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(Constant.Path.FORGOT_PASSWORD)
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email, HttpServletRequest servletRequest) {
        return ResponseEntity.ok().body(authService.forgotPassword(email, servletRequest));
    }

    @PostMapping("/forgot/password/update")
    public ResponseEntity<?> forgotPasswordUpdate(@RequestBody ForgotPasswordRequest request) {
        return ResponseEntity.ok().body(authService.forgotPasswordUpdate(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(authService.login(loginRequest));
    }

    @PostMapping("/refresh/token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok().body(authService.refreshToken(refreshTokenRequest));
    }

    @GetMapping(value = "/encrypt")
    public String encrypt(@RequestParam String email) throws Exception {
        log.info(">>> {}", email);
        String urlEncodedData = URLEncoder.encode(AesUtils.encrypt(email), "UTF-8");
        return urlEncodedData;
    }
}

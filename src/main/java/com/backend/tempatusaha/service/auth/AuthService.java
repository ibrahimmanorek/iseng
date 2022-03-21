package com.backend.tempatusaha.service.auth;

import com.backend.tempatusaha.dto.request.LoginRequest;
import com.backend.tempatusaha.dto.request.RefreshTokenRequest;
import com.backend.tempatusaha.dto.request.ResendOtpRequest;
import com.backend.tempatusaha.dto.request.SignUpRequest;
import com.backend.tempatusaha.dto.response.Response;

public interface AuthService {
    Response save(SignUpRequest signUpRequest);

    Response login(LoginRequest loginRequest);

    Response refreshToken(RefreshTokenRequest refreshTokenRequest);

    Response resendOtp(String email);
}

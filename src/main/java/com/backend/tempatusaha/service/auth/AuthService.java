package com.backend.tempatusaha.service.auth;

import com.backend.tempatusaha.dto.request.*;
import com.backend.tempatusaha.dto.response.Response;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    Response save(SignUpRequest signUpRequest);

    Response login(LoginRequest loginRequest);

    Response refreshToken(RefreshTokenRequest refreshTokenRequest);

    Response resendOtp(String email);

    Response forgotPassword(String email, HttpServletRequest servletRequest);

    Response getForgotPassword(String email);

    Response forgotPasswordUpdate(ForgotPasswordRequest request);
}

package com.backend.tempatusaha.service.account;

import com.backend.tempatusaha.dto.request.SignUpRequest;
import com.backend.tempatusaha.dto.request.VerifyEmailRequest;
import com.backend.tempatusaha.dto.response.Response;
import org.springframework.security.core.Authentication;

public interface AccountService {
    Response getByAccountId(Authentication authentication);

    Response verifyEmail(VerifyEmailRequest verifyEmailRequest);

    Response update(Authentication authentication, SignUpRequest request);
}

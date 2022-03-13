package com.backend.tempatusaha.service;

import com.backend.tempatusaha.dto.request.VerifyEmailRequest;
import com.backend.tempatusaha.dto.response.Response;

public interface AccountService {
    Response getAll(int page, int size);

    Response getByAccountId(long id);

    Response verifyEmail(VerifyEmailRequest verifyEmailRequest);
}

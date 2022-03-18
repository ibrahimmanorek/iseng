package com.backend.tempatusaha.service.admin.account;

import com.backend.tempatusaha.dto.request.SignUpRequest;
import com.backend.tempatusaha.dto.request.VerifyEmailRequest;
import com.backend.tempatusaha.dto.response.Response;
import org.springframework.security.core.Authentication;

public interface AccountAdminService {
    Response getAll(int page, int size);

    Response getByAccountId(long id);

    Response update(long id, SignUpRequest request);
}

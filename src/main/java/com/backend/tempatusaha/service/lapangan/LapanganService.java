package com.backend.tempatusaha.service.lapangan;

import com.backend.tempatusaha.dto.response.Response;
import org.springframework.security.core.Authentication;

public interface LapanganService {
    Response getAll(int page, int size);

    Response distance(Authentication authentication, int page, int size);
}

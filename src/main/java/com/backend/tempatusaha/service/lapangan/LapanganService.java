package com.backend.tempatusaha.service.lapangan;

import com.backend.tempatusaha.dto.request.DistanceRequest;
import com.backend.tempatusaha.dto.response.Response;
import org.springframework.security.core.Authentication;

import java.util.concurrent.ExecutionException;

public interface LapanganService {
    Response getAll(int page, int size);

    Response distance(Authentication authentication, DistanceRequest request, int page, int size) throws ExecutionException, InterruptedException;
}

package com.backend.tempatusaha.service.lapangan;

import com.backend.tempatusaha.dto.request.DistanceRequest;
import com.backend.tempatusaha.dto.request.LapanganRequest;
import com.backend.tempatusaha.dto.response.Response;

public interface LapanganService {
    Response getAll(int page, int size);

    Response getId(long id);

    Response save(LapanganRequest request);

    Response update(long id, LapanganRequest request);

    Response distance(int page, int size, DistanceRequest request);
}

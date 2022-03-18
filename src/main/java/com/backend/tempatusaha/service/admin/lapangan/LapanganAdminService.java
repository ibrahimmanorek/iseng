package com.backend.tempatusaha.service.admin.lapangan;

import com.backend.tempatusaha.dto.request.LapanganRequest;
import com.backend.tempatusaha.dto.response.Response;

public interface LapanganAdminService {
    Response getAll(int page, int size);

    Response getId(long id);

    Response save(LapanganRequest request);

    Response update(long id, LapanganRequest request);
}

package com.backend.tempatusaha.service;

import com.backend.tempatusaha.dto.request.KategoriRequest;
import com.backend.tempatusaha.dto.response.Response;

public interface KategoriService {
    Response getAll(int page, int size);

    Response getId(long id);

    Response save(KategoriRequest request);

    Response update(long id, KategoriRequest request);
}

package com.backend.tempatusaha.service.wilayah;

import com.backend.tempatusaha.dto.response.Response;

public interface WilayahService {
    Response getPropinsiAll(int page, int size);

    Response getPropinsiId(long id);

    Response getKotaId(long id);

    Response getKecamatanId(long id);

    Response getKelurahanId(long id);
}

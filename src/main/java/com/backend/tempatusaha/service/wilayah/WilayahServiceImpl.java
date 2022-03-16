package com.backend.tempatusaha.service.wilayah;

import com.backend.tempatusaha.dto.response.PageResponse;
import com.backend.tempatusaha.dto.response.Response;
import com.backend.tempatusaha.entity.Kategori;
import com.backend.tempatusaha.entity.Kecamatan;
import com.backend.tempatusaha.entity.Propinsi;
import com.backend.tempatusaha.repository.KecamatanRepository;
import com.backend.tempatusaha.repository.KelurahanRepository;
import com.backend.tempatusaha.repository.KotaRepository;
import com.backend.tempatusaha.repository.PropinsiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WilayahServiceImpl implements WilayahService {

    @Autowired
    private PropinsiRepository propinsiRepository;

    @Autowired
    private KotaRepository kotaRepository;

    @Autowired
    private KecamatanRepository kecamatanRepository;

    @Autowired
    private KelurahanRepository kelurahanRepository;

    @Override
    public Response getPropinsiAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Propinsi> propinsiPage = propinsiRepository.findAll(pageable);
        PageResponse pageResponse = PageResponse.builder()
                .totalAllData(propinsiPage.getTotalElements())
                .totalPage(propinsiPage.getTotalPages())
                .currentPage(propinsiPage.getNumber()+1)
                .details(propinsiPage.getContent())
                .build();
        return Response.builder()
                .success(true)
                .message("successfully")
                .data(pageResponse)
                .build();
    }

    @Override
    public Response getPropinsiId(long id) {
        return Response.builder()
                .success(true)
                .message("successfully")
                .data(propinsiRepository.findByIdAndIsaktif(id, 1))
                .build();
    }

    @Override
    public Response getKotaId(long id) {
        return Response.builder()
                .success(true)
                .message("successfully")
                .data(kotaRepository.findByIdAndIsaktif(id, 1))
                .build();
    }

    @Override
    public Response getKecamatanId(long id) {
        return Response.builder()
                .success(true)
                .message("successfully")
                .data(kecamatanRepository.findByIdAndIsaktif(id, 1))
                .build();
    }

    @Override
    public Response getKelurahanId(long id) {
        return Response.builder()
                .success(true)
                .message("successfully")
                .data(kelurahanRepository.findByIdAndIsaktif(id, 1))
                .build();
    }
}

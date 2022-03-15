package com.backend.tempatusaha.service.users;

import com.backend.tempatusaha.dto.request.KategoriRequest;
import com.backend.tempatusaha.dto.response.PageResponse;
import com.backend.tempatusaha.dto.response.Response;
import com.backend.tempatusaha.entity.Kategori;
import com.backend.tempatusaha.exception.ExceptionResponse;
import com.backend.tempatusaha.repository.KategoriRepository;
import com.backend.tempatusaha.service.KategoriService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class KategoriServiceImpl implements KategoriService {

    @Autowired
    private KategoriRepository kategoriRepository;

    @Override
    public Response getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Kategori> kategoriPage = kategoriRepository.findAll(pageable);
        PageResponse pageResponse = PageResponse.builder()
                .totalAllData(kategoriPage.getTotalElements())
                .totalPage(kategoriPage.getTotalPages())
                .currentPage(kategoriPage.getNumber()+1)
                .details(kategoriPage.getContent())
                .build();
        return Response.builder()
                .success(true)
                .message("successfully")
                .data(pageResponse)
                .build();
    }

    @Override
    public Response getId(long id) {
        return Response.builder()
                .success(true)
                .message("successfully")
                .data(kategoriRepository.findById(id))
                .build();
    }

    @Override
    public Response save(KategoriRequest request) {
        return Response.builder()
                .success(true)
                .message("successfully")
                .data(kategoriRepository.save(Kategori.builder()
                        .namaKategori(request.getNamaKategori())
                        .isAktif(1)
                        .createdDate(LocalDateTime.now())
                        .build()))
                .build();
    }

    @Override
    public Response update(long id, KategoriRequest request) {
        Kategori kategori = kategoriRepository.findById(id).orElseThrow(() -> new ExceptionResponse("Data Not Found !!"));
        kategori.setUpdatedDate(LocalDateTime.now());
        kategori.setNamaKategori(request.getNamaKategori());
        return Response.builder()
                .success(true)
                .message("successfully")
                .data(kategori)
                .build();
    }
}

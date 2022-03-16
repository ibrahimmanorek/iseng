package com.backend.tempatusaha.service.lapangan;

import com.backend.tempatusaha.dto.request.LapanganRequest;
import com.backend.tempatusaha.dto.response.PageResponse;
import com.backend.tempatusaha.dto.response.Response;
import com.backend.tempatusaha.entity.*;
import com.backend.tempatusaha.exception.ExceptionResponse;
import com.backend.tempatusaha.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class LapanganServiceImpl implements LapanganService {

    @Autowired
    private LapanganRepository lapanganRepository;

    @Autowired
    private KategoriRepository kategoriRepository;

    @Autowired
    private PropinsiRepository propinsiRepository;

    @Autowired
    private KotaRepository kotaRepository;

    @Autowired
    private KecamatanRepository kecamatanRepository;

    @Autowired
    private KelurahanRepository kelurahanRepository;

    @Override
    public Response getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Lapangan> lapanganPage = lapanganRepository.findAll(pageable);
        PageResponse pageResponse = PageResponse.builder()
                .totalAllData(lapanganPage.getTotalElements())
                .totalPage(lapanganPage.getTotalPages())
                .currentPage(lapanganPage.getNumber()+1)
                .details(lapanganPage.getContent())
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
                .data(lapanganRepository.findById(id))
                .build();
    }

    @Override
    public Response save(LapanganRequest request) {
        Kategori kategori = kategoriRepository.findById(request.getKategoriId()).orElseThrow(() -> new ExceptionResponse("Kategori Not Found"));
        kategori = kategoriRepository.findByIdAndIsAktif(request.getKategoriId(), 1).orElseThrow(() -> new ExceptionResponse("Kategori Tidak Aktif"));

        Propinsi propinsi = propinsiRepository.findById(request.getPropinsiId()).orElseThrow(() -> new ExceptionResponse("Propinsi Not Found"));
        Kota kota = kotaRepository.findById(request.getKotaId()).orElseThrow(() -> new ExceptionResponse("Kota Not Found"));
        Kecamatan kecamatan = kecamatanRepository.findById(request.getKecamatanId()).orElseThrow(() -> new ExceptionResponse("Kecamatan Not Found"));
        Kelurahan kelurahan = kelurahanRepository.findById(request.getKelurahanId()).orElseThrow(() -> new ExceptionResponse("Kelurahan Not Found"));

        return Response.builder()
                .success(true)
                .message("successfully")
                .data(lapanganRepository.save(Lapangan.builder()
                        .kategoriId(kategori)
                        .namaLapangan(request.getNamaLapangan())
                        .namaPic(request.getNamaPic())
                        .phoneNumber(request.getPhoneNumber())
                        .email(request.getEmail())
                        .address(request.getAddress())
                        .longitude(request.getLongitude())
                        .latitude(request.getLatitude())
                        .propinsiId(propinsi)
                        .kotaId(kota)
                        .kecamatanId(kecamatan)
                        .kelurahanId(kelurahan)
                        .isAktif(1)
                        .createdDate(LocalDateTime.now())
                        .build()))
                .build();
    }

    @Override
    public Response update(long id, LapanganRequest request) {
        Lapangan lapangan = lapanganRepository.findById(id).orElseThrow(() -> new ExceptionResponse("Data Not Found"));
        lapangan = lapanganRepository.findByIdAndIsAktif(id, 1).orElseThrow(() -> new ExceptionResponse("Lapangan Tidak Aktif"));
        lapangan.setKategoriId(Kategori.builder().id(request.getKategoriId()).build());
        lapangan.setNamaLapangan(request.getNamaLapangan());
        lapangan.setNamaPic(request.getNamaPic());
        lapangan.setEmail(request.getEmail());
        lapangan.setPhoneNumber(request.getPhoneNumber());
        lapangan.setAddress(request.getAddress());
        lapangan.setLongitude(request.getLongitude());
        lapangan.setLatitude(request.getLatitude());
        lapangan.setPropinsiId(Propinsi.builder().id(request.getPropinsiId()).build());
        lapangan.setKotaId(Kota.builder().id(request.getKotaId()).build());
        lapangan.setKecamatanId(Kecamatan.builder().id(request.getKecamatanId()).build());
        lapangan.setKelurahanId(Kelurahan.builder().id(request.getKelurahanId()).build());
        return Response.builder()
                .success(true)
                .message("successfully")
                .data(lapanganRepository.save(lapangan))
                .build();
    }
}

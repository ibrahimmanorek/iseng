package com.backend.tempatusaha.service.lapangan;

import com.backend.tempatusaha.dto.request.DistanceRequest;
import com.backend.tempatusaha.dto.request.WilayahRequest;
import com.backend.tempatusaha.dto.response.PageResponse;
import com.backend.tempatusaha.dto.response.Response;
import com.backend.tempatusaha.entity.*;
import com.backend.tempatusaha.exception.ExceptionResponse;
import com.backend.tempatusaha.repository.*;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class LapanganServiceImpl implements LapanganService {

    @Autowired
    private LapanganRepository lapanganRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private KelurahanRepository kelurahanRepository;

    @Value("${distance.value}")
    private String distance;

    private Gson gson = new Gson();

    @Override
    public Response getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Lapangan> lapanganPage = lapanganRepository.findAll(pageable);
        PageResponse pageResponse = PageResponse.builder()
                .totalAllData(lapanganPage.getTotalElements())
                .totalPage(lapanganPage.getTotalPages())
                .currentPage(lapanganPage.getNumber() + 1)
                .details(lapanganPage.getContent())
                .build();
        return Response.builder()
                .success(true)
                .message("successfully")
                .data(pageResponse)
                .build();
    }

    @Override
    public Response distance(Authentication authentication, DistanceRequest request, int page, int size) throws ExecutionException, InterruptedException {
//        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(() -> new ExceptionResponse("Akun Not Found"));
//        account = accountRepository.findByIdAndIsAktif(account.getId(), 1).orElseThrow(() -> new ExceptionResponse("Akun Tidak Aktif"));

        CompletableFuture<Account> future1 = findUsername(authentication.getName());
        CompletableFuture<Account> future2 = findAccountIsAktif(1);
        Account account = future2.get();

        int totalPage = page * 10;
        CompletableFuture<Integer> future3 = findCountLapanganWithDistance(request, distance);
        CompletableFuture<List<Lapangan>> future4 = findLapanganWithInDistance(request, distance, totalPage, size);

//        int totalData = lapanganRepository.findCountLapanganWithDistance(account.getLatitude(), account.getLongitude(), distance);
//        List<Lapangan> lapanganPage = lapanganRepository.findLapanganWithInDistance(account.getLatitude(), account.getLongitude(), distance, totalPage, size);
        PageResponse pageResponse = PageResponse.builder()
                .totalAllData(future3.get().intValue())
                .totalPage(page)
                .currentPage(page + 1)
                .details(future4.get())
                .build();
        return Response.builder()
                .success(true)
                .message("successfully")
                .data(pageResponse)
                .build();
    }

    CompletableFuture<Account> findUsername(String username) {
        return CompletableFuture.supplyAsync(() -> {
            return accountRepository.findByUsername(username).orElseThrow(() -> new ExceptionResponse("username not found"));
        });
    }

    CompletableFuture<Account> findAccountIsAktif(long id) {
        return CompletableFuture.supplyAsync(() -> {
            return accountRepository.findByIdAndIsAktif(id, 1).orElseThrow(() -> new ExceptionResponse("akun tidak aktif"));
        });
    }

    CompletableFuture<Integer> findCountLapanganWithDistance(DistanceRequest request, String distance){
        return CompletableFuture.supplyAsync(() -> {
           return lapanganRepository.findCountLapanganWithDistance(request.getLatitude(), request.getLongitude(), distance);
        });
    }

    CompletableFuture<List<Lapangan>> findLapanganWithInDistance(DistanceRequest request, String distance, int totalPage, int size){
        return CompletableFuture.supplyAsync(() -> {
            return lapanganRepository.findLapanganWithInDistance(request.getLatitude(), request.getLongitude(), distance, totalPage, size);
        });
    }

    @Override
    public Response wilayah(Authentication authentication, WilayahRequest request, int page, int size) {
        Propinsi propinsi = Propinsi.builder().id(request.getPropinsiId()).build();
        Kota kota = Kota.builder().id(request.getKotaId()).build();
        Kecamatan kecamatan = Kecamatan.builder().id(request.getKecamatanId()).build();
        Kelurahan kelurahan = Kelurahan.builder().id(request.getKelurahanId()).build();

        Pageable pageable = PageRequest.of(page, size);
        Page<Lapangan> lapanganPage = lapanganRepository.findByPropinsiIdOrKotaIdOrKecamatanIdOrKelurahanIdAndIsaktif(propinsi, kota, kecamatan, kelurahan, 1, pageable);

        PageResponse pageResponse = PageResponse.builder()
                .totalAllData(lapanganPage.getTotalElements())
                .totalPage(page)
                .currentPage(page + 1)
                .details(lapanganPage.getContent())
                .build();
        return Response.builder()
                .success(true)
                .message("successfully")
                .data(pageResponse)
                .build();
    }
}

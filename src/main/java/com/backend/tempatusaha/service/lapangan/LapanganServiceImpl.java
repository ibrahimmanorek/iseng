package com.backend.tempatusaha.service.lapangan;

import com.backend.tempatusaha.dto.request.DistanceRequest;
import com.backend.tempatusaha.dto.request.LapanganRequest;
import com.backend.tempatusaha.dto.response.PageResponse;
import com.backend.tempatusaha.dto.response.Response;
import com.backend.tempatusaha.entity.*;
import com.backend.tempatusaha.exception.ExceptionResponse;
import com.backend.tempatusaha.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LapanganServiceImpl implements LapanganService {

    @Autowired
    private LapanganRepository lapanganRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Value("${distance.value}")
    private String distance;

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
    public Response distance(Authentication authentication, int page, int size) {
        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(() -> new ExceptionResponse("Akun Not Found"));
        account = accountRepository.findByIdAndIsAktif(account.getId(), 1).orElseThrow(() -> new ExceptionResponse("Akun Tidak Aktif"));
        int totalPage = page * 10;
        int totalData = lapanganRepository.findCountLapanganWithDistance(account.getLatitude(), account.getLongitude(), distance);
        List<Lapangan> lapanganPage = lapanganRepository.findLapanganWithInDistance(account.getLatitude(), account.getLongitude(), distance, totalPage, size);
        PageResponse pageResponse = PageResponse.builder()
                .totalAllData(totalData)
                .totalPage(page)
                .currentPage(page + 1)
                .details(lapanganPage)
                .build();
        return Response.builder()
                .success(true)
                .message("successfully")
                .data(pageResponse)
                .build();
    }
}

package com.backend.tempatusaha.service.admin.account;

import com.backend.tempatusaha.dto.request.SignUpRequest;
import com.backend.tempatusaha.dto.response.PageResponse;
import com.backend.tempatusaha.dto.response.Response;
import com.backend.tempatusaha.entity.Account;
import com.backend.tempatusaha.exception.ExceptionResponse;
import com.backend.tempatusaha.repository.AccountRepository;
import com.backend.tempatusaha.repository.OtpRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class AccountAdminServiceImpl implements AccountAdminService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OtpRepository otpRepository;

    @Override
    public Response getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Account> accountPage = accountRepository.findAll(pageable);
        PageResponse pageResponse = PageResponse.builder()
                .totalAllData(accountPage.getTotalElements())
                .totalPage(accountPage.getTotalPages())
                .currentPage(accountPage.getNumber()+1)
                .details(accountPage.getContent())
                .build();
        return Response.builder()
                .success(true)
                .message("successfully")
                .data(pageResponse)
                .build();
    }

    @Override
    public Response getByAccountId(long id) {
        return Response.builder()
                .success(true)
                .message("successfully")
                .data(accountRepository.findById(id))
                .build();
    }

    @Override
    public Response update(long id, SignUpRequest request) {
        Account account = accountRepository.findByIdAndIsAktif(id, 1).orElseThrow(() -> new ExceptionResponse("Account Not Found"));
        account.setPhoneNumber(request.getPhoneNumber());
        account.setBank(request.getBank());
        account.setRekening(request.getRekening());
        account.setAddress(request.getAddress());
        account.setUpdatedDate(LocalDateTime.now());
        account = accountRepository.save(account);
        return Response.builder()
                .success(true)
                .message("successfully")
                .data(account)
                .build();
    }
}

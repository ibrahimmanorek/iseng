package com.backend.tempatusaha.service;

import com.backend.tempatusaha.dto.response.PageResponse;
import com.backend.tempatusaha.dto.response.Response;
import com.backend.tempatusaha.entity.Account;
import com.backend.tempatusaha.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

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
}

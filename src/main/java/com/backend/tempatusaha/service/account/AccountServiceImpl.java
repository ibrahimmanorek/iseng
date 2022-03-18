package com.backend.tempatusaha.service.account;

import com.backend.tempatusaha.dto.request.SignUpRequest;
import com.backend.tempatusaha.dto.request.VerifyEmailRequest;
import com.backend.tempatusaha.dto.response.Response;
import com.backend.tempatusaha.entity.Account;
import com.backend.tempatusaha.entity.Otp;
import com.backend.tempatusaha.exception.ExceptionResponse;
import com.backend.tempatusaha.repository.AccountRepository;
import com.backend.tempatusaha.repository.OtpRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OtpRepository otpRepository;

    @Override
    public Response getByAccountId(Authentication authentication) {
        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(() -> new ExceptionResponse("Account Not Found"));
        return Response.builder()
                .success(true)
                .message("successfully")
                .data(accountRepository.findById(account.getId()))
                .build();
    }

    @Override
    public Response verifyEmail(VerifyEmailRequest verifyEmailRequest) {
        if(verifyEmailRequest.getOtp().length() != 6)
            throw new ExceptionResponse("OTP 6 CHAR");

        Account account = accountRepository.findById(verifyEmailRequest.getId())
                .orElseThrow(() -> new ExceptionResponse("ACCOUNT ID TIDAK VALID"));

        account = accountRepository.findByIdAndIsAktif(verifyEmailRequest.getId(), 1)
                .orElseThrow(() -> new ExceptionResponse("ACCOUNT ID TIDAK AKTIF"));

        Otp otp = otpRepository.findByAccountIdAndFlag(verifyEmailRequest.getId(), 0)
                .orElseThrow(() -> new ExceptionResponse("OTP NOT FOUND"));

        if(!verifyEmailRequest.getOtp().equals(otp.getOtp()))
            throw new ExceptionResponse("OTP TIDAK VALID");

        account.setIsAktif(1);
        account.setUpdatedDate(LocalDateTime.now());
        accountRepository.save(account);
        return Response.builder()
                .success(true)
                .message("successfully")
                .build();
    }

    @Override
    public Response update(Authentication authentication, SignUpRequest request) {
        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(() -> new ExceptionResponse("Account Not Found"));
        account = accountRepository.findByIdAndIsAktif(account.getId(), 1).orElseThrow(() -> new ExceptionResponse("Account Not Found"));
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

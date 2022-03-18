package com.backend.tempatusaha.controller;

import com.backend.tempatusaha.dto.request.SignUpRequest;
import com.backend.tempatusaha.dto.request.VerifyEmailRequest;
import com.backend.tempatusaha.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/getAccount")
    public ResponseEntity<?> getAccountId(Authentication authentication){
        return ResponseEntity.ok().body(accountService.getByAccountId(authentication));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(Authentication authentication, @RequestBody SignUpRequest request){
        return ResponseEntity.ok().body(accountService.update(authentication, request));
    }

    @PostMapping("/verify/email")
    public ResponseEntity<?> verifyEmail(@RequestBody VerifyEmailRequest verifyEmailRequest){
        return ResponseEntity.ok().body(accountService.verifyEmail(verifyEmailRequest));
    }
}

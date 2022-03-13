package com.backend.tempatusaha.controller;

import com.backend.tempatusaha.dto.request.VerifyEmailRequest;
import com.backend.tempatusaha.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", required = true) int page,
                                    @RequestParam(value = "size", required = true) int size){
        return ResponseEntity.ok().body(accountService.getAll(page, size));
    }

    @GetMapping("/getAccountId/{id}")
    public ResponseEntity<?> getAccountId(@PathVariable("id") long id){
        return ResponseEntity.ok().body(accountService.getByAccountId(id));
    }

    @PostMapping("/verify/email")
    public ResponseEntity<?> verifyEmail(@RequestBody VerifyEmailRequest verifyEmailRequest){
        return ResponseEntity.ok().body(accountService.verifyEmail(verifyEmailRequest));
    }
}

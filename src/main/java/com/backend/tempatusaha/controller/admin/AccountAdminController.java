package com.backend.tempatusaha.controller.admin;

import com.backend.tempatusaha.dto.request.SignUpRequest;
import com.backend.tempatusaha.service.admin.account.AccountAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/account")
@PreAuthorize("hasAuthority('ADMIN')")
public class AccountAdminController {

    @Autowired
    private AccountAdminService accountAdminService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", required = true) int page,
                                    @RequestParam(value = "size", required = true) int size){
        return ResponseEntity.ok().body(accountAdminService.getAll(page, size));
    }

    @GetMapping("/getAccount/{id}")
    public ResponseEntity<?> getAccountId(@PathVariable("id") long id){
        return ResponseEntity.ok().body(accountAdminService.getByAccountId(id));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id,@RequestBody SignUpRequest request){
        return ResponseEntity.ok().body(accountAdminService.update(id, request));
    }
}

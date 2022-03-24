package com.backend.tempatusaha.controller;

import com.backend.tempatusaha.dto.response.Response;
import com.backend.tempatusaha.entity.Account;
import com.backend.tempatusaha.entity.Role;
import com.backend.tempatusaha.repository.AccountRepository;
import com.backend.tempatusaha.service.auth.AuthService;
import com.backend.tempatusaha.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class LoginSSOController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    http://localhost:8083/oauth2/authorization/google
    @GetMapping("/home")
    public ResponseEntity<?> loginGoogle(@AuthenticationPrincipal OAuth2User principal) {
        String name = principal.getName();
        String givenName = principal.getAttribute("given_name");
        String familyName = principal.getAttribute("family_name");
        String picture = principal.getAttribute("picture");
        String email = principal.getAttribute("email");
        log.info("name : {}", name);
        log.info("givenName : {}", givenName);
        log.info("familyName : {}", familyName);
        log.info("picture : {}", picture);
        log.info("email : {}", email);
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        map.put("att", principal.getAttributes());

        if(!accountRepository.existsByEmail(email)){
            accountRepository.save(Account.builder()
                    .email(email)
                    .username(givenName)
                    .password(passwordEncoder.encode("123456"))
                    .createdDate(LocalDateTime.now())
                    .isAktif(1)
                    .providerLogin(Constant.ProviderLogin.GOOGLE)
                    .roleId(Role.builder().id(2).build())
                    .build());
        }

        return ResponseEntity.ok().body(Response.builder().success(true).message("successfully").data(map).build());
    }

    @GetMapping("/login")
    public ResponseEntity<?> login() {
        Map<String, Object> map = new HashMap<>();
        map.put("version", "0.1");
        return ResponseEntity.ok().body(Response.builder().success(true).message("successfully").data(map).build());
    }

}

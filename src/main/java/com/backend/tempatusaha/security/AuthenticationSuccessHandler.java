package com.backend.tempatusaha.security;

import com.backend.tempatusaha.entity.Account;
import com.backend.tempatusaha.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component("oauth2authSuccessHandler")
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder encoder;


    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
        String firstName = null;
        if (accountRepository.findByUsername(authentication.getName()) == null) {
            firstName = authenticationToken.getPrincipal().getAttributes().get("given_name").toString();
            String email = authenticationToken.getPrincipal().getAttributes().get("email").toString();
            accountRepository.save(Account.builder()
                    .email(email)
                    .username(firstName)
                    .createdDate(LocalDateTime.now())
                    .isAktif(1)
                    .build());
        }
        this.redirectStrategy.sendRedirect(request, response,firstName!=null?"/hello?name="+firstName:"/hello");
    }
}

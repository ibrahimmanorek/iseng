package com.backend.tempatusaha.service.users;

import com.backend.tempatusaha.entity.Account;
import com.backend.tempatusaha.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  AccountRepository accountRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = accountRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Account Not Found with username: " + username));

    return UserDetailsImpl.build(account);
  }

  public List<Account> findAll() {
    return accountRepository.findAll();
  }
}

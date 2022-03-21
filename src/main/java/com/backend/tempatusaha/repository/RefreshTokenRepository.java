package com.backend.tempatusaha.repository;

import com.backend.tempatusaha.entity.Account;
import com.backend.tempatusaha.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByToken(String token);

  Optional<RefreshToken> findByAccountId(Account account);

  @Modifying
  int deleteByAccountId(long accountId);
}

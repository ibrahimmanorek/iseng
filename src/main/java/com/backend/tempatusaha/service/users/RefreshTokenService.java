package com.backend.tempatusaha.service.users;

import com.backend.tempatusaha.entity.Account;
import com.backend.tempatusaha.entity.RefreshToken;
import com.backend.tempatusaha.exception.TokenRefreshException;
import com.backend.tempatusaha.repository.AccountRepository;
import com.backend.tempatusaha.repository.RefreshTokenRepository;
import com.backend.tempatusaha.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
public class RefreshTokenService {
  @Value("${config.app.jwtRefreshExpirationMs}")
  private Long refreshTokenDurationMs;

  @Autowired
  private RefreshTokenRepository refreshTokenRepository;

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private AccountRepository accountRepository;

  public Optional<RefreshToken> findByToken(String token) {
    return refreshTokenRepository.findByToken(token);
  }

  public RefreshToken createRefreshToken(Long userId) {
    RefreshToken refreshToken = new RefreshToken();
    Optional<Account> optionalUser = accountRepository.findById(userId);
    refreshToken.setAccountId(optionalUser.get());
    refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
//    refreshToken.setToken(UUID.randomUUID().toString());
    refreshToken.setToken(jwtUtils.generateRefreshTokenFromUsername(optionalUser.get().getUsername()));
    refreshToken = refreshTokenRepository.save(refreshToken);
    return refreshToken;
  }

  public RefreshToken verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(token);
      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
    }

    return token;
  }

  @Transactional
  public int deleteByUserId(Long userId) {
    return refreshTokenRepository.deleteByAccountId(accountRepository.findById(userId).get().getId());
  }
}

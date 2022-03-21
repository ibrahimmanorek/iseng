package com.backend.tempatusaha.repository;

import com.backend.tempatusaha.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsernameAndPassword(String username, String password);

    Optional<Account> findByUsername(String username);

    Optional<Account> findByUsernameAndIsAktif(String username, int isaktif);

    Optional<Account> findByEmailAndIsAktif(String username, int isaktif);

    Optional<Account> findByIdAndIsAktif(long id, int isaktif);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}

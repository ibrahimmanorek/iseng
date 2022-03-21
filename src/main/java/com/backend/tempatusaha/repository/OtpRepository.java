package com.backend.tempatusaha.repository;

import com.backend.tempatusaha.entity.Account;
import com.backend.tempatusaha.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findByAccountIdAndFlag(long id, int flag);
    Optional<Otp> findByAccountIdAndFlagAndTipeOtp(Account account, int flag, String tipeOtp);

    @Modifying
    @Transactional
    @Query(value = "update otp set flag = :flag, updated_date = now(), info = :info where account_id = :id", nativeQuery = true)
    void updateFlag(String id, int flag);
}

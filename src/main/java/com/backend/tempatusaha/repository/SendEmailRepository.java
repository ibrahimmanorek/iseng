package com.backend.tempatusaha.repository;

import com.backend.tempatusaha.entity.SendEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SendEmailRepository extends JpaRepository<SendEmail, Long> {
    List<SendEmail> findAllByFlag(int flag);

    @Modifying
    @Transactional
    @Query(value = "update send_email set flag = :flag, updated_date = now(), info = :info where email_to = :email", nativeQuery = true)
    void updateFlag(String email, int flag, String info);
}

package com.backend.tempatusaha.repository;

import com.backend.tempatusaha.entity.LapanganDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LapanganDetailRepository extends JpaRepository<LapanganDetail, Long> {
    Optional<LapanganDetail> findByIdAndIsaktif(long id, int isaktif);
}

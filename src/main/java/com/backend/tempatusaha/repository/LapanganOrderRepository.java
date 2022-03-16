package com.backend.tempatusaha.repository;

import com.backend.tempatusaha.entity.LapanganOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LapanganOrderRepository extends JpaRepository<LapanganOrder, Long> {
    Optional<LapanganOrder> findByIdAndIsaktif(long id, int isaktif);
}

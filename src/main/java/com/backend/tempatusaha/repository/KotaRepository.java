package com.backend.tempatusaha.repository;

import com.backend.tempatusaha.entity.Kota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KotaRepository extends JpaRepository<Kota, Long> {
    Optional<Kota> findByIdAndIsaktif(long id, int isaktif);
}

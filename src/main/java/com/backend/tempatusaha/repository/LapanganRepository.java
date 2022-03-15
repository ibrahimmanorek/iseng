package com.backend.tempatusaha.repository;

import com.backend.tempatusaha.entity.Lapangan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LapanganRepository extends JpaRepository<Lapangan, Long> {
    Optional<Lapangan> findByIdAndIsAktif(long id, int isaktif);
}

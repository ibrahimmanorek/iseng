package com.backend.tempatusaha.repository;

import com.backend.tempatusaha.entity.Kecamatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KecamatanRepository extends JpaRepository<Kecamatan, Long> {
    Optional<Kecamatan> findByIdAndIsaktif(long id, int isaktif);
}

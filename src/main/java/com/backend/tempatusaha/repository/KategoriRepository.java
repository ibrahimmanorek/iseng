package com.backend.tempatusaha.repository;

import com.backend.tempatusaha.entity.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KategoriRepository extends JpaRepository<Kategori, Long> {
    Optional<Kategori> findByIdAndIsAktif(long id, int isaktif);
}

package com.backend.tempatusaha.repository;

import com.backend.tempatusaha.entity.Kecamatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KecamatanRepository extends JpaRepository<Kecamatan, Long> {
}

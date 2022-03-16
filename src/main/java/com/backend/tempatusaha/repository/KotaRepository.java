package com.backend.tempatusaha.repository;

import com.backend.tempatusaha.entity.Kota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KotaRepository extends JpaRepository<Kota, Long> {
}

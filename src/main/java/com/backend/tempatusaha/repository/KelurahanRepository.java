package com.backend.tempatusaha.repository;

import com.backend.tempatusaha.entity.Kelurahan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KelurahanRepository extends JpaRepository<Kelurahan, Long> {
}

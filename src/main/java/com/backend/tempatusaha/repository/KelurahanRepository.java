package com.backend.tempatusaha.repository;

import com.backend.tempatusaha.entity.Kelurahan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KelurahanRepository extends JpaRepository<Kelurahan, Long> {
    Optional<Kelurahan> findByIdAndIsaktif(long id, int isaktif);
}

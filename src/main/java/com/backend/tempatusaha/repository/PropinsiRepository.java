package com.backend.tempatusaha.repository;

import com.backend.tempatusaha.entity.Propinsi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropinsiRepository extends JpaRepository<Propinsi, Long> {
    Optional<Propinsi> findByIdAndIsaktif(long id, int isaktif);
}

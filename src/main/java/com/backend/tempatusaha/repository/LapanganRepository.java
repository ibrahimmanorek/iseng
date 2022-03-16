package com.backend.tempatusaha.repository;

import com.backend.tempatusaha.entity.Lapangan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LapanganRepository extends JpaRepository<Lapangan, Long> {
    Optional<Lapangan> findByIdAndIsAktif(long id, int isaktif);
    Optional<Lapangan> findByPropinsiIdAndIsAktif(long id, int isaktif);
    Optional<Lapangan> findByPropinsiIdAndKotaIdAndIsAktif(long propinsiId, long kotaId, int isaktif);
    Optional<Lapangan> findByPropinsiIdAndKotaIdAndKecamatanIdAndIsAktif(long propinsiId, long kotaId, long kecamatanId, int isaktif);
    Optional<Lapangan> findByPropinsiIdAndKotaIdAndKecamatanIdAndKelurahanIdAndIsAktif(long propinsiId, long kotaId, long kecamatanId, long kelurahanId, int isaktif);
}

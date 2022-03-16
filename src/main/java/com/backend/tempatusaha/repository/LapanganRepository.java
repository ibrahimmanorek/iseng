package com.backend.tempatusaha.repository;

import com.backend.tempatusaha.entity.Lapangan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LapanganRepository extends JpaRepository<Lapangan, Long> {
    Optional<Lapangan> findByIdAndIsaktif(long id, int isaktif);
    Optional<Lapangan> findByPropinsiIdAndIsaktif(long id, int isaktif);
    Optional<Lapangan> findByPropinsiIdAndKotaIdAndIsaktif(long propinsiId, long kotaId, int isaktif);
    Optional<Lapangan> findByPropinsiIdAndKotaIdAndKecamatanIdAndIsaktif(long propinsiId, long kotaId, long kecamatanId, int isaktif);
    Optional<Lapangan> findByPropinsiIdAndKotaIdAndKecamatanIdAndKelurahanIdAndIsaktif(long propinsiId, long kotaId, long kecamatanId, long kelurahanId, int isaktif);
}

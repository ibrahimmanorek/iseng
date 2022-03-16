package com.backend.tempatusaha.repository;

import com.backend.tempatusaha.entity.Lapangan;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LapanganRepository extends JpaRepository<Lapangan, Long> {
    Optional<Lapangan> findByIdAndIsaktif(long id, int isaktif);

    Optional<Lapangan> findByPropinsiIdAndIsaktif(long id, int isaktif);

    Optional<Lapangan> findByPropinsiIdAndKotaIdAndIsaktif(long propinsiId, long kotaId, int isaktif);

    Optional<Lapangan> findByPropinsiIdAndKotaIdAndKecamatanIdAndIsaktif(long propinsiId, long kotaId, long kecamatanId, int isaktif);

    Optional<Lapangan> findByPropinsiIdAndKotaIdAndKecamatanIdAndKelurahanIdAndIsaktif(long propinsiId, long kotaId, long kecamatanId, long kelurahanId, int isaktif);

    String HAVERSINE_PART = "(6371 * acos(cos(radians(:latitude)) * cos(radians(s.latitude)) *" +
            " cos(radians(s.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(s.latitude))))";

    @Query(value = "SELECT s.*, " + HAVERSINE_PART + " distance FROM lapangan s WHERE " + HAVERSINE_PART + " < :distance ORDER BY distance ASC limit :page, :sizes", nativeQuery = true)
    List<Lapangan> findLapanganWithInDistance(
            @Param("latitude") String latitude,
            @Param("longitude") String longitude,
            @Param("distance") String distanceWithInKM,
            @Param("page") int page,
            @Param("sizes") int size);

    @Query(value = "SELECT count(*) FROM lapangan s WHERE " + HAVERSINE_PART + " < :distance", nativeQuery = true)
    int findCountLapanganWithDistance(
            @Param("latitude") String latitude,
            @Param("longitude") String longitude,
            @Param("distance") String distanceWithInKM);
}

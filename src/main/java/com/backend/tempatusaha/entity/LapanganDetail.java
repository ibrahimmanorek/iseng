package com.backend.tempatusaha.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lapangan_detail",
indexes = {
        @Index(columnList = "tipe_lapangan")
})
public class LapanganDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lapangan_images_id", referencedColumnName = "id")
    private LapanganImages lapanganImagesId;

    @ManyToOne
    @JoinColumn(name = "lapangan_harga_id", referencedColumnName = "id")
    private LapanganHarga lapanganHargaId;

    @ManyToOne
    @JoinColumn(name = "lapangan_libur_id", referencedColumnName = "id")
    private LapanganLibur lapanganLibur;

    @Column(name = "tipe_lapangan", nullable = false, length = 150)
    private String tipeLapangan;

    @Column(name = "isaktif", columnDefinition = "integer default 1")
    private Integer isaktif;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "created_date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;
}

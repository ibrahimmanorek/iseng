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
@Table(name = "lapangan",
indexes = {
        @Index(columnList = "nama_lapangan"),
        @Index(columnList = "latitude"),
        @Index(columnList = "longitude")
})
public class Lapangan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kategori_id", referencedColumnName = "id")
    private Kategori kategoriId;

    @ManyToOne
    @JoinColumn(name = "propinsi_id", referencedColumnName = "id")
    private Propinsi propinsiId;

    @ManyToOne
    @JoinColumn(name = "kota_id", referencedColumnName = "id")
    private Kota kotaId;

    @ManyToOne
    @JoinColumn(name = "kecamatan_id", referencedColumnName = "id")
    private Kecamatan kecamatanId;

    @ManyToOne
    @JoinColumn(name = "kelurahan_id", referencedColumnName = "id")
    private Kelurahan kelurahanId;

    @Column(name = "nama_lapangan", nullable = false, length = 150)
    private String namaLapangan;

    @Column(name = "nama_pic", nullable = false, length = 150)
    private String namaPic;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "latitude", nullable = false, length = 50)
    private Long latitude;

    @Column(name = "longitude", nullable = false, length = 50)
    private Long longitude;

    @Column(name = "isaktif", nullable = false, columnDefinition = "integer default 1")
    private int isAktif;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "created_date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;
}

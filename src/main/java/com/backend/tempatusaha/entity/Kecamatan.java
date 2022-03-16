package com.backend.tempatusaha.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kecamatan",
indexes = {
        @Index(columnList = "nama_kecamatan")
})
public class Kecamatan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kota_id", referencedColumnName = "id")
    private Kota kotaId;

    @Column(name = "nama_kecamatan", length = 150)
    private String namaKecamatan;

    @Column(name = "isaktif", columnDefinition = "integer default 1")
    private int isaktif;

    @Column(name = "created_date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;
}

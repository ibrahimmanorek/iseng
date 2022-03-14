package com.backend.tempatusaha.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "katalog_detail")
public class KatalogDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lapangan_id", referencedColumnName = "id")
    private Lapangan lapanganId;

    @ManyToOne
    @JoinColumn(name = "katalog_id", referencedColumnName = "id")
    private Katalog katalogId;

    @Column(name = "tanggal_jam")
    private Date tanggalJam;

    @Column(name = "status", length = 100)
    private String status;

    @Column(name = "harga")
    private Long harga;

    @Column(name = "isaktif", columnDefinition = "integer default 1")
    private Integer isaktif;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "created_date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;
}
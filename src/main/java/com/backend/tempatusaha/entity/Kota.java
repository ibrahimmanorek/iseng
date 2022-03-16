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
@Table(name = "kota",
indexes = {
        @Index(columnList = "nama_kota")
})
public class Kota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "propinsi_id", referencedColumnName = "id")
    private Propinsi propinsiId;

    @Column(name = "nama_kota", length = 150)
    private String namaKota;

    @Column(name = "isaktif", columnDefinition = "integer default 1")
    private int isaktif;

    @Column(name = "created_date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;
}

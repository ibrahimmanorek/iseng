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
    @JoinColumn(name = "lapangan_id", referencedColumnName = "id")
    private Lapangan lapanganId;

    @Column(name = "tipe_lapangan", nullable = false, length = 150)
    private String tipeLapangan;

    @Column(name = "pict1", length = 150)
    private String pict1;

    @Column(name = "pict2", length = 150)
    private String pict2;

    @Column(name = "pict3", length = 150)
    private String pict3;

    @Column(name = "isaktif", columnDefinition = "integer default 1")
    private Integer isaktif;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "created_date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;
}

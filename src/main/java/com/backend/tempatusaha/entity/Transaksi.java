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
@Table(name = "transaksi")
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lapangan_id", referencedColumnName = "id")
    private Lapangan lapanganId;

    @ManyToOne
    @JoinColumn(name = "lapangan_detail_id", referencedColumnName = "id")
    private LapanganDetail lapanganDetailId;

    @Column(name = "invoice", length = 50)
    private String invoice;

    @Column(name = "kode_unik")
    private int kodeUnik;

    @Column(name = "status", length = 100)
    private String status;

    @Column(name = "harga")
    private Long harga;

    @Column(name = "promo_id")
    private Long promoId;

    @Column(name = "admin")
    private Long admin;

    @Column(name = "payment_by")
    private String paymentBy;

    @Column(name = "date_payment")
    private LocalDateTime datePayment;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "created_date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;
}

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
@Table(name = "otp",
        indexes = {
                @Index(columnList = "flag"),
                @Index(columnList = "tipe_otp")
        })
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account accountId;

    @Column(name = "otp", nullable = false, length = 6)
    private String otp;

    @Column(name = "tipe_otp", nullable = false, length = 50)
    private String tipeOtp;

    @Column(name = "flag", columnDefinition = "integer default 0")
    private int flag;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "created_date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;
}

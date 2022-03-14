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
@Table(name = "send_email",
indexes = {
        @Index(columnList = "email_to"),
        @Index(columnList = "tipe_email"),
        @Index(columnList = "flag")
})
public class SendEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account accountId;

    @Column(name = "email_to", nullable = false, length = 150)
    private String emailTo;

    @Column(name = "email_from", nullable = false, length = 150)
    private String emailFrom;

    @Column(name = "flag", columnDefinition = "integer default 0")
    private int flag;

    @Column(name = "tipe_email", nullable = false, length = 50)
    private String tipeEmail;

    @ManyToOne
    @JoinColumn(name = "otp_id", referencedColumnName = "id")
    private Otp otpId;

    @Column(name = "info", columnDefinition = "TEXT")
    private String info;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "created_date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;
}

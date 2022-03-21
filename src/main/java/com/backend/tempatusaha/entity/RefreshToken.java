package com.backend.tempatusaha.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "refresh_token", indexes = {
        @Index(columnList = "token")
})
public class RefreshToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  @JoinColumn(name = "account_id", referencedColumnName = "id")
  private Account accountId;

  @Column(nullable = false)
  private String token;

  @Column(nullable = false)
  private Instant expiryDate;

}

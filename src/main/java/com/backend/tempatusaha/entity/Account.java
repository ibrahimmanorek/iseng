package com.backend.tempatusaha.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account",
indexes = {
        @Index(name = "uniqueIndex", columnList = "username", unique = true)
})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", nullable = false, length = 150)
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false, length = 150)
    private String password;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "bank", length = 50)
    private String bank;

    @Column(name = "rekening", length = 50)
    private String rekening;

    @Column(name = "latitude", length = 50)
    private String latitude;

    @Column(name = "longitude", length = 50)
    private String longitude;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(	name = "account_role",
//            joinColumns = @JoinColumn(name = "account_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> role = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role roleId;

    @Column(name = "isaktif", nullable = false, columnDefinition = "integer default 1")
    private int isAktif;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "created_date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;
}

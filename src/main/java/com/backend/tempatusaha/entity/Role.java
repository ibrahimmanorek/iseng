package com.backend.tempatusaha.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role",
indexes = {
        @Index(name = "uniqueIndex", columnList = "roleName", unique = true)
})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rolename", nullable = false, length = 150)
    private ERole roleName;

    @Column(name = "isaktif", nullable = false, columnDefinition = "integer default 1")
    private int isAktif;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "created_date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdDate;
}

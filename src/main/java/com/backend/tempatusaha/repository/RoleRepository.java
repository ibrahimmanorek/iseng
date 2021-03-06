package com.backend.tempatusaha.repository;

import com.backend.tempatusaha.entity.ERole;
import com.backend.tempatusaha.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleNameAndIsAktif(ERole rolename, int isaktif);
    Optional<Role> findByIdAndIsAktif(int id, int isaktif);
}

package com.studio.loyalty.repositories;

import com.studio.loyalty.entities.EnumEntity;
import com.studio.loyalty.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    boolean existsByRolename(EnumEntity.ROLES rolename);

    boolean existsById(String id);

    Optional<RoleEntity> findById(String id);

    Optional<RoleEntity> findByRolename(EnumEntity.ROLES rolename);

}

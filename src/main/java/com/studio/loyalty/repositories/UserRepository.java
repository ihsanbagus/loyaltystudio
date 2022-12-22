package com.studio.loyalty.repositories;

import com.studio.loyalty.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    boolean existsById(String id);

    Optional<UserEntity> findById(String id);

    Optional<UserEntity> findByEmail(String email);

}

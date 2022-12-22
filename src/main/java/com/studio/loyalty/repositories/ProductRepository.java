package com.studio.loyalty.repositories;

import com.studio.loyalty.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    boolean existsById(String id);

    Optional<ProductEntity> findById(String id);

}
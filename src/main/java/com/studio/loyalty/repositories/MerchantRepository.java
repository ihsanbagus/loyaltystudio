package com.studio.loyalty.repositories;

import com.studio.loyalty.entities.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<MerchantEntity, String> {

    boolean existsById(String id);

    Optional<MerchantEntity> findById(String id);

}

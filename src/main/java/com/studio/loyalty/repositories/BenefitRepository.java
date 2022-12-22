package com.studio.loyalty.repositories;

import com.studio.loyalty.entities.BenefitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BenefitRepository extends JpaRepository<BenefitEntity, String> {

    boolean existsById(String id);

    Optional<BenefitEntity> findById(String id);

}

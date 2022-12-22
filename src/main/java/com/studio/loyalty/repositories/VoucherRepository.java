package com.studio.loyalty.repositories;

import com.studio.loyalty.entities.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<VoucherEntity, String> {

    boolean existsById(String id);

    Optional<VoucherEntity> findById(String id);

}

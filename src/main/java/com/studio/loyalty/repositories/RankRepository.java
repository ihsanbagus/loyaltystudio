package com.studio.loyalty.repositories;

import com.studio.loyalty.entities.EnumEntity;
import com.studio.loyalty.entities.RankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RankRepository extends JpaRepository<RankEntity, String> {

    boolean existsByRankname(EnumEntity.RANK rankname);

    boolean existsById(String id);

    Optional<RankEntity> findById(String id);

    Optional<RankEntity> findByRankname(EnumEntity.RANK rankname);

}

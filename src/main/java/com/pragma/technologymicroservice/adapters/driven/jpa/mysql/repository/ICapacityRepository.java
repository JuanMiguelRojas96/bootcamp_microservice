package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICapacityRepository extends JpaRepository <CapacityEntity, Long> {

  Optional<CapacityEntity> findByName(String name);
  @Query("SELECT c.technologies FROM CapacityEntity c WHERE c.id = :capacityId")
  List<TechnologyEntity> findTechnologiesByCapacityId(@Param("capacityId") Long capacityId);
}

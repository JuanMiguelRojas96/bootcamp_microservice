package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.BootcampEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBootcampRepository extends JpaRepository<BootcampEntity, Long> {

  Optional<BootcampEntity> findByName(String name);
  Page<BootcampEntity> findAll(Pageable pageable);

}

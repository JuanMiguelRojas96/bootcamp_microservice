package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITechnologyRepository extends JpaRepository<TechnologyEntity, Long>  {

  List<TechnologyEntity> findByName(String name);

  Page<TechnologyEntity> findAll(Pageable pageable);
}

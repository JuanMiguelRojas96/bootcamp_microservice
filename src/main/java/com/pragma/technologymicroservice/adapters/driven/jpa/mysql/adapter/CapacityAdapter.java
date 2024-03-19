package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.adapter;


import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.exception.CapacityAlreadyExistsException;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.exception.CapacityMaxTechnologiesException;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.exception.RepeatTechInCapacityException;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.technologymicroservice.domain.model.Capacity;
import com.pragma.technologymicroservice.domain.spi.ICapacityPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
public class CapacityAdapter implements ICapacityPersistencePort {

  private final ICapacityRepository capacityRepository;
  private final ICapacityEntityMapper capacityEntityMapper;

  @Override
  public void saveCapacity(Capacity capacity) {

    String normalizedCapName = StringUtils.capitalize(capacity.getName().toLowerCase());

    if (capacityRepository.findByName(normalizedCapName).isPresent()){
      throw new CapacityAlreadyExistsException();
    }
    capacity.setName(normalizedCapName);
/*
    List<TechnologyEntity> technologies = capacityRepository.findTechnologiesByCapacityId(capacity.getId());
    if(technologies.size() < 3 || technologies.size() > 20) {
      throw new CapacityMaxTechnologiesException();
    }

    if (technologies.stream()
        .map(tech -> tech.getId())
        .distinct()
        .count() != technologies.size()){
      throw new RepeatTechInCapacityException();
    }*/
    capacityRepository.save(capacityEntityMapper.toEntity(capacity));

  }
}

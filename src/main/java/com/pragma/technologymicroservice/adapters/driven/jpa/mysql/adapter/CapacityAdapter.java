package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.adapter;


import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExistsException;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.technologymicroservice.domain.model.Capacity;
import com.pragma.technologymicroservice.domain.model.Technology;
import com.pragma.technologymicroservice.domain.spi.ICapacityPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CapacityAdapter implements ICapacityPersistencePort {

  private final ICapacityRepository capacityRepository;
  private final ICapacityEntityMapper capacityEntityMapper;
  private final ITechnologyRepository technologyRepository;

  @Override
  public void saveCapacity(Capacity capacity) {

    String normalizedCapName = capacity.getName().toLowerCase();

    if (capacity.getTechnologies() != null && !capacity.getTechnologies().isEmpty()) {
      for (Technology technology : capacity.getTechnologies()) {
        List<TechnologyEntity> existingTechnology = technologyRepository.findByName(technology.getName());
        if (existingTechnology.size() > 0) {
          throw new TechnologyAlreadyExistsException();
        }
      }
      capacity.setName(normalizedCapName);
      capacityRepository.save(capacityEntityMapper.toEntity(capacity));

    }
  }
}

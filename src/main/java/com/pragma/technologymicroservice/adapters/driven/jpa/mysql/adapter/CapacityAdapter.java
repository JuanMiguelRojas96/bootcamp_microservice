package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.adapter;


import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.exception.CapacityMaxTechnologiesException;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.exception.RepeatTechInCapacityException;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExistsException;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.technologymicroservice.domain.model.Capacity;
import com.pragma.technologymicroservice.domain.model.Technology;
import com.pragma.technologymicroservice.domain.spi.ICapacityPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CapacityAdapter implements ICapacityPersistencePort {

  private final ICapacityRepository capacityRepository;
  private final ICapacityEntityMapper capacityEntityMapper;
  private final ITechnologyRepository technologyRepository;



  @Override
  public void saveCapacity(Capacity capacity) {

    String normalizedCapName = capacity.getName().toLowerCase();
    capacity.setName(normalizedCapName);

    if (capacity.getTechnologies() != null && !capacity.getTechnologies().isEmpty()) {
      List<TechnologyEntity> technologyEntities = new ArrayList<>();

      for (Technology technology : capacity.getTechnologies()) {
        Optional<TechnologyEntity> existingTechnology = technologyRepository.findById(technology.getId());

        if (existingTechnology.isPresent()) {
          Long technologyId = existingTechnology.get().getId();

          if (technologyEntities.stream().anyMatch(t -> t.getId().equals(technologyId))) {
            throw new RepeatTechInCapacityException();
          }

          technologyEntities.add(existingTechnology.get());

        } else {
          throw new NoDataFoundException();
        }
      }

      CapacityEntity capacityEntity = capacityEntityMapper.toEntity(capacity);
      capacityEntity.setTechnologies(technologyEntities);
      capacityRepository.save(capacityEntity);
    }
  }

}

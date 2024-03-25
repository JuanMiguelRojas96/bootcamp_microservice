package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.exception.RepeatCapInBootcampException;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.technologymicroservice.domain.model.Bootcamp;
import com.pragma.technologymicroservice.domain.model.Capacity;
import com.pragma.technologymicroservice.domain.spi.IBootcampPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BootcampAdapter implements IBootcampPersistencePort {


  private final IBootcampRepository bootcampRepository;
  private final IBootcampEntityMapper bootcampEntityMapper;
  private final ICapacityRepository capacityRepository;

  @Override
  public void saveBootcamp(Bootcamp bootcamp) {

    String normalizedCapName = bootcamp.getName().trim().toLowerCase();
    bootcamp.setName(normalizedCapName);

    if (bootcamp.getCapacities() != null && !bootcamp.getCapacities().isEmpty()){

      List<CapacityEntity> capacityEntities = new ArrayList<>();

      for (Capacity capacity : bootcamp.getCapacities()){
        Optional<CapacityEntity> existingCapacity = capacityRepository.findById(capacity.getId());

        if (existingCapacity.isPresent()){
          Long capacityId = existingCapacity.get().getId();

          if (capacityEntities.stream().anyMatch(c -> c.getId().equals(capacityId))) {
            throw new RepeatCapInBootcampException();
          }

          capacityEntities.add(existingCapacity.get());

        }else {
          throw new NoDataFoundException();
        }
      }

      BootcampEntity bootcampEntity = bootcampEntityMapper.toEntity(bootcamp);
      bootcampEntity.setCapacities(capacityEntities);
      bootcampRepository.save(bootcampEntity);
    }
  }
}

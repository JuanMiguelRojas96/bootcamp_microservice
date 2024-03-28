package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.technologymicroservice.utils.exception.NoDataFoundException;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.technologymicroservice.domain.model.Bootcamp;
import com.pragma.technologymicroservice.domain.model.Capacity;
import com.pragma.technologymicroservice.domain.spi.IBootcampPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Comparator;
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

  @Override
  public List<Bootcamp> getAllBootcamps(Integer page, Integer size, boolean orderBootcamp, boolean orderCapacity) {

    Pageable pagination = PageRequest.of(page,size);

    List<BootcampEntity> bootcamps = bootcampRepository.findAll(pagination).getContent();
    List<BootcampEntity> bootcampEntities = new ArrayList<>(bootcamps);

    bootcampEntities.sort(orderCapacity ? Comparator.comparingInt(e ->e.getCapacities().size()) : Comparator.comparing(e -> e.getCapacities().size(), Comparator.<Integer>reverseOrder()));
    bootcampEntities.sort(orderBootcamp ? Comparator.comparing(BootcampEntity::getName) : Comparator.comparing(BootcampEntity::getName, Comparator.reverseOrder()));

    if(bootcamps.isEmpty()){
      throw new NoDataFoundException();
    }
    return bootcampEntityMapper.toModelList(bootcampEntities);
  }
}

package com.pragma.technologymicroservice.domain.api.usecase;

import com.pragma.technologymicroservice.utils.exception.RepeatCapInBootcampException;
import com.pragma.technologymicroservice.domain.api.IBootcampServicePort;
import com.pragma.technologymicroservice.domain.model.Bootcamp;
import com.pragma.technologymicroservice.domain.model.Capacity;
import com.pragma.technologymicroservice.domain.spi.IBootcampPersistencePort;

import java.util.List;

public class BootcampUseCase implements IBootcampServicePort {

  private IBootcampPersistencePort bootcampPersistencePort;

  public BootcampUseCase(IBootcampPersistencePort bootcampPersistencePort) {
    this.bootcampPersistencePort = bootcampPersistencePort;
  }

  @Override
  public void saveBootcamp(Bootcamp bootcamp) {

    List<Capacity> capacities = bootcamp.getCapacities();

    for (Capacity capacity : capacities){
      Long capacityId = capacity.getId();

      if (capacities.stream().anyMatch(c -> c.getId().equals(capacityId))){
        throw new RepeatCapInBootcampException();
      }
    }
    bootcampPersistencePort.saveBootcamp(bootcamp);
  }

  @Override
  public List<Bootcamp> getAllBootcamps(Integer page, Integer size, boolean orderBootcamp, boolean orderCapacity){
    return bootcampPersistencePort.getAllBootcamps(page, size, orderBootcamp, orderCapacity);
  }
}

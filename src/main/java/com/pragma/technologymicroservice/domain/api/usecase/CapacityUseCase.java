package com.pragma.technologymicroservice.domain.api.usecase;

import com.pragma.technologymicroservice.domain.api.ICapacityServicePort;
import com.pragma.technologymicroservice.domain.model.Capacity;
import com.pragma.technologymicroservice.domain.model.Technology;
import com.pragma.technologymicroservice.domain.spi.ICapacityPersistencePort;
import com.pragma.technologymicroservice.utils.exception.RepeatTechInCapacityException;

import java.util.ArrayList;
import java.util.List;

public class CapacityUseCase implements ICapacityServicePort {

  private ICapacityPersistencePort  capacityPersistencePort;

  public CapacityUseCase(ICapacityPersistencePort capacityPersistencePort) {
    this.capacityPersistencePort = capacityPersistencePort;
  }

  @Override
  public void saveCapacity(Capacity capacity) {

    List<Long> technologies = new ArrayList<>();

    for (Technology technology: capacity.getTechnologies()){
      Long technologyId = technology.getId();

      if (technologies.contains(technologyId)){
        throw new RepeatTechInCapacityException();
      }else {
        technologies.add(technologyId);
      }
    }
    capacityPersistencePort.saveCapacity(capacity);
  }

  @Override
  public List<Capacity> getAllCapacities(Integer page, Integer size, boolean orderCapacity, boolean orderTech) {
    return capacityPersistencePort.getAllCapacities(page,size,orderCapacity,orderTech);
  }
}

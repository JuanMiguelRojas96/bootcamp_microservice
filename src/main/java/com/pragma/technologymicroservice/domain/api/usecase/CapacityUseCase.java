package com.pragma.technologymicroservice.domain.api.usecase;

import com.pragma.technologymicroservice.domain.api.ICapacityServicePort;
import com.pragma.technologymicroservice.domain.model.Capacity;
import com.pragma.technologymicroservice.domain.spi.ICapacityPersistencePort;

import java.util.List;

public class CapacityUseCase implements ICapacityServicePort {

  private ICapacityPersistencePort  capacityPersistencePort;

  public CapacityUseCase(ICapacityPersistencePort capacityPersistencePort) {
    this.capacityPersistencePort = capacityPersistencePort;
  }

  @Override
  public void saveCapacity(Capacity capacity) {
    capacityPersistencePort.saveCapacity(capacity);

  }

  @Override
  public List<Capacity> getAllCapacities(Integer page, Integer size) {
    return capacityPersistencePort.getAllCapacities(page,size);
  }
}

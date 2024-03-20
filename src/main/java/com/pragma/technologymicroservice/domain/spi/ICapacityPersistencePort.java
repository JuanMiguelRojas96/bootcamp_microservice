package com.pragma.technologymicroservice.domain.spi;

import com.pragma.technologymicroservice.domain.model.Capacity;

public interface ICapacityPersistencePort {

  void saveCapacity(Capacity capacity);
}

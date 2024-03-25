package com.pragma.technologymicroservice.domain.spi;

import com.pragma.technologymicroservice.domain.model.Capacity;

import java.util.List;

public interface ICapacityPersistencePort {

  void saveCapacity(Capacity capacity);

  List<Capacity> getAllCapacities(Integer page, Integer size, boolean orderCapacity, boolean orderTech);
}

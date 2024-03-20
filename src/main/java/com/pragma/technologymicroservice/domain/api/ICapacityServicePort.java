package com.pragma.technologymicroservice.domain.api;

import com.pragma.technologymicroservice.domain.model.Capacity;

public interface ICapacityServicePort {

  void saveCapacity(Capacity capacity);
}

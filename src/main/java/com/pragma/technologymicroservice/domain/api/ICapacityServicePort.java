package com.pragma.technologymicroservice.domain.api;

import com.pragma.technologymicroservice.domain.model.Capacity;

import java.util.List;

public interface ICapacityServicePort {

  void saveCapacity(Capacity capacity);

  List<Capacity> getAllCapacities(Integer page, Integer size);
}

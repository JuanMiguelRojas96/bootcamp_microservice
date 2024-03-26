package com.pragma.technologymicroservice.domain.spi;

import com.pragma.technologymicroservice.domain.model.Bootcamp;

import java.util.List;

public interface IBootcampPersistencePort {

  void saveBootcamp(Bootcamp bootcamp);

  List<Bootcamp> getAllBootcamps(Integer page, Integer size, boolean orderBootcamp, boolean orderCapacity);
}

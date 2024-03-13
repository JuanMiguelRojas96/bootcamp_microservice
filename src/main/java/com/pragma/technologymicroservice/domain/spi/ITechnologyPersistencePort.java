package com.pragma.technologymicroservice.domain.spi;

import com.pragma.technologymicroservice.domain.model.Technology;


public interface ITechnologyPersistencePort {

  void saveTechnology(Technology technology);
}

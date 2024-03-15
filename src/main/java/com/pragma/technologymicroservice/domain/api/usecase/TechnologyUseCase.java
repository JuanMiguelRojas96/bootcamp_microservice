package com.pragma.technologymicroservice.domain.api.usecase;

import com.pragma.technologymicroservice.domain.api.ITechnologyServicePort;
import com.pragma.technologymicroservice.domain.model.Technology;
import com.pragma.technologymicroservice.domain.spi.ITechnologyPersistencePort;

import java.util.List;

public class TechnologyUseCase implements ITechnologyServicePort {

  private ITechnologyPersistencePort technologyPersistencePort;

  public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort) {
    this.technologyPersistencePort = technologyPersistencePort;
  }


  @Override
  public void saveTechnology(Technology technology) {
    technologyPersistencePort.saveTechnology(technology);
  }

  @Override
  public List<Technology> getAllTechnologies(Integer page, Integer size, boolean ascending) {
    return technologyPersistencePort.getAllTechnologies(page,size,ascending);
  }

}

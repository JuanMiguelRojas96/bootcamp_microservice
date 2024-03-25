package com.pragma.technologymicroservice.domain.api.usecase;

import com.pragma.technologymicroservice.domain.api.IBootcampServicePort;
import com.pragma.technologymicroservice.domain.model.Bootcamp;
import com.pragma.technologymicroservice.domain.spi.IBootcampPersistencePort;

public class BootcampUseCase implements IBootcampServicePort {

  private IBootcampPersistencePort bootcampPersistencePort;

  public BootcampUseCase(IBootcampPersistencePort bootcampPersistencePort) {
    this.bootcampPersistencePort = bootcampPersistencePort;
  }

  @Override
  public void saveBootcamp(Bootcamp bootcamp) {
    bootcampPersistencePort.saveBootcamp(bootcamp);
  }
}

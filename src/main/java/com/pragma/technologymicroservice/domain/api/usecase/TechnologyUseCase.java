package com.pragma.technologymicroservice.domain.api.usecase;

import com.pragma.technologymicroservice.domain.api.ITechnologyServicePort;
import com.pragma.technologymicroservice.domain.exception.EmptyFieldException;
import com.pragma.technologymicroservice.domain.exception.MaxCharException;
import com.pragma.technologymicroservice.domain.model.Technology;
import com.pragma.technologymicroservice.domain.spi.ITechnologyPersistencePort;
import com.pragma.technologymicroservice.domain.util.DomainConstants;

import java.util.List;

public class TechnologyUseCase implements ITechnologyServicePort {

  private ITechnologyPersistencePort technologyPersistencePort;

  public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort) {
    this.technologyPersistencePort = technologyPersistencePort;
  }


  @Override
  public void saveTechnology(Technology technology) {
    if (technology.getName().trim().isEmpty()){
      throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
    }
    if (technology.getDescription().trim().isEmpty()){
      throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
    }
    if (technology.getName().length() > 50){
      throw new MaxCharException(DomainConstants.MAX_CHAR_NAME_MESSAGE);
    }
    if (technology.getDescription().length() > 90){
      throw new MaxCharException(DomainConstants.MAX_CHAR_DESCRIPTION_MESSAGE);
    }

    technologyPersistencePort.saveTechnology(technology);
  }

}

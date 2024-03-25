package com.pragma.technologymicroservice.domain.spi;

import com.pragma.technologymicroservice.domain.model.Bootcamp;

public interface IBootcampPersistencePort {

  void saveBootcamp(Bootcamp bootcamp);
}

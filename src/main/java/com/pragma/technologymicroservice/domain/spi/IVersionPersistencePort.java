package com.pragma.technologymicroservice.domain.spi;

import com.pragma.technologymicroservice.domain.model.Version;

public interface IVersionPersistencePort {

  void saveVersion(Version version);
}

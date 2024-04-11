package com.pragma.technologymicroservice.domain.api.usecase;

import com.pragma.technologymicroservice.domain.api.IVersionServicePort;
import com.pragma.technologymicroservice.domain.model.Version;

public class VersionUseCase implements IVersionServicePort {

  private IVersionServicePort versionPersistencePort;

  public VersionUseCase(IVersionServicePort versionPersistencePort) {

    this.versionPersistencePort = versionPersistencePort;

  }

  @Override
  public void saveVersion(Version version) {

    versionPersistencePort.saveVersion(version);
  }
}

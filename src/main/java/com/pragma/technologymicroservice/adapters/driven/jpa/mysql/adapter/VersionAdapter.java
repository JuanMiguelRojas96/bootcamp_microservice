package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.pragma.technologymicroservice.domain.model.Version;
import com.pragma.technologymicroservice.domain.spi.IVersionPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VersionAdapter implements IVersionPersistencePort {

  private final IVersionRepository versionRepository;
  private final IVersionEntityMapper versionEntityMapper;
  @Override
  public void saveVersion(Version version) {
    VersionEntity versionEntity = versionEntityMapper.toEntity(version);
    versionRepository.save(versionEntity);
  }
}

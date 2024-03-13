package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExistsException;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.technologymicroservice.domain.model.Technology;
import com.pragma.technologymicroservice.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {
  private final ITechnologyRepository technologyRepository;
  private final ITechnologyEntityMapper technologyEntityMapper;

  @Override
  public void saveTechnology(Technology technology) {
    if(technologyRepository.findByName(technology.getName()).isPresent()){
      throw new TechnologyAlreadyExistsException();
    }
    technologyRepository.save(technologyEntityMapper.toEntity(technology));

  }
}

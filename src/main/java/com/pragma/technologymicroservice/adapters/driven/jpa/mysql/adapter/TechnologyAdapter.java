package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExistsException;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.technologymicroservice.domain.model.Technology;
import com.pragma.technologymicroservice.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;


@RequiredArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {
  private final ITechnologyRepository technologyRepository;
  private final ITechnologyEntityMapper technologyEntityMapper;

  @Override
  public void saveTechnology(Technology technology) {

    String normalizedTechName = StringUtils.capitalize(technology.getName().toLowerCase());

    if(technologyRepository.findByName(normalizedTechName).isPresent()){
      throw new TechnologyAlreadyExistsException();
    }

    technology.setName(normalizedTechName);
    technologyRepository.save(technologyEntityMapper.toEntity(technology));
  }
}

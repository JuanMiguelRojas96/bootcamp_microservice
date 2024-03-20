package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExistsException;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.technologymicroservice.domain.model.Technology;
import com.pragma.technologymicroservice.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.List;


@RequiredArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {
  private final ITechnologyRepository technologyRepository;
  private final ITechnologyEntityMapper technologyEntityMapper;

  @Override
  public void saveTechnology(Technology technology) {

    String normalizedTechName = technology.getName().toLowerCase();

    if(technologyRepository.findByName(normalizedTechName).size() > 0){
      throw new TechnologyAlreadyExistsException();
    }

    technology.setName(normalizedTechName);
    technologyRepository.save(technologyEntityMapper.toEntity(technology));
  }

  @Override
  public List<Technology> getAllTechnologies(Integer page, Integer size, boolean ascending) {
    Sort sort = ascending ? Sort.by(Sort.Direction.ASC, "name") : Sort.by(Sort.Direction.DESC, "name");
    Pageable pagination = PageRequest.of(page, size, sort);
    List<TechnologyEntity> technologies = technologyRepository.findAll(pagination).getContent();

    if (technologies.isEmpty()) {
      throw new NoDataFoundException();
    }

    return technologyEntityMapper.toModelList(technologies);
  }
}

package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExistsException;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.technologymicroservice.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


class TechnologyAdapterTest {

  @Mock
  private ITechnologyRepository technologyRepository;

  @Mock
  private ITechnologyEntityMapper technologyEntityMapper;

  private TechnologyAdapter technologyAdapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    technologyAdapter = new TechnologyAdapter(technologyRepository, technologyEntityMapper);
  }

  @Test
  void saveTechnology_SuccessfulSave() {

    Technology technology = new Technology(2L, "Java", "Programming Language");
    TechnologyEntity technologyEntity = new TechnologyEntity();

    when(technologyRepository.findByName(technology.getName())).thenReturn(Optional.empty());
    when(technologyEntityMapper.toEntity(technology)).thenReturn(technologyEntity);

    technologyAdapter.saveTechnology(technology);

    verify(technologyRepository).save(technologyEntity);
  }

  @Test
  void saveTechnology_AlreadyExists() {

    Technology technology = new Technology(2L, "Java", "Programming Language");
    TechnologyEntity technologyEntity = new TechnologyEntity();

    when(technologyRepository.findByName(technology.getName())).thenReturn(Optional.of(technologyEntity));

    assertThrows(TechnologyAlreadyExistsException.class, () -> technologyAdapter.saveTechnology(technology));
  }

}
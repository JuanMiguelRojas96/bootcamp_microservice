package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.pragma.technologymicroservice.domain.model.Bootcamp;
import com.pragma.technologymicroservice.domain.model.Version;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VersionAdapterTest {

  @Mock
  private IVersionRepository versionRepository;
  @Mock
  private IVersionEntityMapper versionEntityMapper;

  @InjectMocks
  private VersionAdapter versionAdapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSaveVersion() {

    LocalDate initialDate = LocalDate.now();
    LocalDate finalDate = LocalDate.now();
    Integer cupos = 1;
    Bootcamp bootcamp = new Bootcamp(1L,"name","description",new ArrayList<>());

    Version version = new Version(1L,initialDate,finalDate,cupos,bootcamp);

    VersionEntity versionEntity = new VersionEntity(1L,initialDate,finalDate,cupos,new BootcampEntity());

    when(versionEntityMapper.toEntity(version)).thenReturn(versionEntity);

    versionAdapter.saveVersion(version);

    verify(versionRepository).save(versionEntity);

  }
}
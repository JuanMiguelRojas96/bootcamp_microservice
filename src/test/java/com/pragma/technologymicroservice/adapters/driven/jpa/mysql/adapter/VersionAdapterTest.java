package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.pragma.technologymicroservice.domain.model.Bootcamp;
import com.pragma.technologymicroservice.domain.model.Version;

import com.pragma.technologymicroservice.utils.exception.NoDataFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

  @Test
  void testGetAllVersions() {

    List<VersionEntity> versionEntities = new ArrayList<>();

    VersionEntity versionEntity1 = new VersionEntity(1L, LocalDate.now(), LocalDate.now(), 1, new BootcampEntity());
    VersionEntity versionEntity2 = new VersionEntity(2L, LocalDate.now(), LocalDate.now(), 2, new BootcampEntity());

    versionEntities.add(versionEntity1);
    versionEntities.add(versionEntity2);

    Page<VersionEntity> versionEntityPage = new PageImpl<>(versionEntities);

    when(versionRepository.findAll(any(PageRequest.class))).thenReturn(versionEntityPage);

    List<Version> expectedVersions = List.of(
        new Version(1L, LocalDate.now(), LocalDate.now(), 1, new Bootcamp(1L,"name","description",new ArrayList<>())),
        new Version(2L, LocalDate.now(), LocalDate.now(), 2, new Bootcamp(1L,"name","description",new ArrayList<>()))
    );
    when(versionEntityMapper.toModelList(versionEntities)).thenReturn(expectedVersions);

    List<Version> versions = versionAdapter.getAllVersions(0, 10, null, null, null, null);

    assertEquals(2, versions.size());
    assertEquals(versionEntity1.getId(), versions.get(0).getId());
    assertEquals(versionEntity2.getId(), versions.get(1).getId());
    assertEquals(expectedVersions, versions);

    verify(versionRepository).findAll(PageRequest.of(0, 10));
    verify(versionEntityMapper).toModelList(versionEntities);
  }

  @Test
  void testGetAllVersions_NoDataFoundException() {

    when(versionRepository.findByBootcamp_Id(any(), any(PageRequest.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

    assertThrows(NoDataFoundException.class, () -> versionAdapter.getAllVersions(0, 10, null , null, null, 1L));
  }
}
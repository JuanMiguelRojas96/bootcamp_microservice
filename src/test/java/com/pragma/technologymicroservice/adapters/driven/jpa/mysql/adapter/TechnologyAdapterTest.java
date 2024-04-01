package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.technologymicroservice.utils.exception.NoDataFoundException;
import com.pragma.technologymicroservice.utils.exception.TechnologyAlreadyExistsException;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.technologymicroservice.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TechnologyAdapterTest {

  @Mock
  private ITechnologyRepository technologyRepository;

  @Mock
  private ITechnologyEntityMapper technologyEntityMapper;

  @InjectMocks
  private TechnologyAdapter technologyAdapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void saveTechnologySuccessfulSave() {
    Technology technology = new Technology(2L, "Java", "Programming Language");
    TechnologyEntity technologyEntity = new TechnologyEntity();

    when(technologyRepository.findByName(technology.getName())).thenReturn(Optional.of(technologyEntity));
    when(technologyEntityMapper.toEntity(technology)).thenReturn(technologyEntity);

    assertDoesNotThrow(() -> technologyAdapter.saveTechnology(technology));

    verify(technologyRepository).save(technologyEntity);
  }

  @Test
  void saveTechnologyAlreadyExists() {
    Technology technology = new Technology(2L, "java", "Programming Language");
    TechnologyEntity technologyEntity = new TechnologyEntity(2L, "java", "Programming Language",List.of());

    when(technologyRepository.findByName(technology.getName())).thenReturn(Optional.of(technologyEntity));

    assertThrows(TechnologyAlreadyExistsException.class, () -> technologyAdapter.saveTechnology(technology));
  }

  @Test
  void getAllTechnologiesSuccess() {
    int page = 0;
    int size = 10;
    boolean ascending = true;

    PageRequest pageable = PageRequest.of(page, size, Sort.by(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, "name"));
    TechnologyEntity technologyEntity = new TechnologyEntity();
    technologyEntity.setName("Java");
    Page<TechnologyEntity> pageImpl = new PageImpl<>(Collections.singletonList(technologyEntity), pageable, 1);

    when(technologyRepository.findAll(pageable)).thenReturn(pageImpl);
    when(technologyEntityMapper.toModelList(Collections.singletonList(technologyEntity))).thenReturn(Collections.singletonList(new Technology(2L, "Java", "Programming Language")));

    List<Technology> expected = Collections.singletonList(new Technology(2L, "Java", "Programming Language"));
    List<Technology> actual = technologyAdapter.getAllTechnologies(page, size, ascending);

    assertEquals(expected.size(), actual.size());

    for (int i = 0; i < expected.size(); i++) {
      Technology expectedTech = expected.get(i);
      Technology actualTech = actual.get(i);
      assertEquals(expectedTech.getName(), actualTech.getName());
    }

    verify(technologyRepository).findAll(pageable);
    verify(technologyEntityMapper).toModelList(Collections.singletonList(technologyEntity));
  }

  @Test
  void getAllTechnologiesFailureNoDataFound() {
    int page = 0;
    int size = 10;
    boolean ascending = true;

    PageRequest pageable = PageRequest.of(page, size, Sort.by(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, "name"));

    when(technologyRepository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.emptyList()));

    assertThrows(NoDataFoundException.class, () -> technologyAdapter.getAllTechnologies(page, size, ascending));

    verify(technologyRepository).findAll(pageable);
    verifyNoInteractions(technologyEntityMapper);
  }
}

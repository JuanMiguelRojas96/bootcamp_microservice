package com.pragma.technologymicroservice.domain.api.usecase;

import com.pragma.technologymicroservice.domain.api.ITechnologyServicePort;
import com.pragma.technologymicroservice.domain.model.Technology;
import com.pragma.technologymicroservice.domain.spi.ITechnologyPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TechnologyUseCaseTest {
  @Mock
  private ITechnologyPersistencePort technologyPersistencePort;
  @InjectMocks
  private TechnologyUseCase technologyUseCase;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSaveTechnology() {

    Technology technology = new Technology(2L,"Java","Programing Language");

    technologyUseCase.saveTechnology(technology);

    verify(technologyPersistencePort, times(1)).saveTechnology(technology);
  }

  @Test
  void testGetAllTechnologies() {
    List<Technology> expectedTechnologies = new ArrayList<>();
    expectedTechnologies.add(new Technology(2L, "Java","Programing"));
    expectedTechnologies.add(new Technology(3L, "Python","Programing"));

    when(technologyPersistencePort.getAllTechnologies(1, 10, true)).thenReturn(expectedTechnologies);

    List<Technology> actualTechnologies = technologyUseCase.getAllTechnologies(1, 10, true);

    assertEquals(expectedTechnologies.size(), actualTechnologies.size());
    for (int i = 0; i < expectedTechnologies.size(); i++) {
      assertEquals(expectedTechnologies.get(i).getName(), actualTechnologies.get(i).getName());
    }
  }



}
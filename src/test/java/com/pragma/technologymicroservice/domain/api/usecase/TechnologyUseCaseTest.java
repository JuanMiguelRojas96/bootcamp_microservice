package com.pragma.technologymicroservice.domain.api.usecase;

import com.pragma.technologymicroservice.domain.api.ITechnologyServicePort;
import com.pragma.technologymicroservice.domain.model.Technology;
import com.pragma.technologymicroservice.domain.spi.ITechnologyPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TechnologyUseCaseTest {
  @Mock
  private ITechnologyPersistencePort technologyPersistencePort;

  private ITechnologyServicePort technologyServicePort;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    technologyServicePort = new TechnologyUseCase(technologyPersistencePort);
  }

  @Test
  void testSaveTechnology() {

    Technology technology = new Technology(2L,"Java","Programing Language");

    technologyServicePort.saveTechnology(technology);

    verify(technologyPersistencePort, times(1)).saveTechnology(technology);
  }

  @Test
  void testGetAllTechnologies() {
    // Arrange
    List<Technology> expectedTechnologies = new ArrayList<>();
    expectedTechnologies.add(new Technology(2L, "Java","Programing"));
    expectedTechnologies.add(new Technology(3L, "Python","Programing"));

    // Simulate behavior of technologyPersistencePort
    when(technologyPersistencePort.getAllTechnologies(1, 10, true)).thenReturn(expectedTechnologies);

    // Act
    List<Technology> actualTechnologies = technologyServicePort.getAllTechnologies(1, 10, true);

    // Assert
    assertEquals(expectedTechnologies.size(), actualTechnologies.size());
    for (int i = 0; i < expectedTechnologies.size(); i++) {
      assertEquals(expectedTechnologies.get(i).getName(), actualTechnologies.get(i).getName());
    }
  }



}
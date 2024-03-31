package com.pragma.technologymicroservice.domain.api.usecase;

import com.pragma.technologymicroservice.domain.model.Capacity;
import com.pragma.technologymicroservice.domain.model.Technology;
import com.pragma.technologymicroservice.domain.spi.ICapacityPersistencePort;
import com.pragma.technologymicroservice.utils.exception.RepeatTechInCapacityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


class CapacityUseCaseTest {

  @Mock
  private ICapacityPersistencePort capacityPersistencePort;

  private CapacityUseCase capacityUseCase;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    capacityUseCase = new CapacityUseCase(capacityPersistencePort);
  }

  private Capacity createCapacityWithTechnologies() {
    List<Technology> technologies = new ArrayList<>();

    technologies.add(new Technology(1L, "Java", "Programming Language"));
    technologies.add(new Technology(2L, "Spring Boot", "Framework"));

    return new Capacity(1L, "CapacityName", "CapacityDescription", technologies);
  }

  @Test
  void testSaveCapacity() {

    Capacity capacity = createCapacityWithTechnologies();

    capacityUseCase.saveCapacity(capacity);

    verify(capacityPersistencePort, times(1)).saveCapacity(capacity);
  }

  @Test
  void testRepeatTechInCapacityException(){
    List<Technology> technologies = new ArrayList<>();
    technologies.add(new Technology(1L, "Java", "Programming Language"));
    technologies.add(new Technology(1L, "Spring Boot", "Framework"));

    Capacity capacity = new Capacity(1L, "CapacityName", "CapacityDescription", technologies);

    doThrow(new RepeatTechInCapacityException()).when(capacityPersistencePort).saveCapacity(capacity);

    assertThrows(RepeatTechInCapacityException.class, () -> {
      capacityUseCase.saveCapacity(capacity);
    });

    verify(capacityPersistencePort, never()).saveCapacity(capacity);
  }

  @Test
  void testGetAllCapacities(){

    Capacity capacity = createCapacityWithTechnologies();

    List<Capacity> capacities = new ArrayList<>();

    capacities.add(capacity);

    when(capacityPersistencePort.getAllCapacities(1,10,true,true)).thenReturn(capacities);

    List<Capacity> actualCapacities = capacityPersistencePort.getAllCapacities(1,10,true,true);

    assertEquals(capacities.size(),actualCapacities.size());
    for (int i = 0; i < capacities.size(); i++){
      assertEquals(capacities.get(i).getName(), actualCapacities.get(i).getName());
    }
  }
}



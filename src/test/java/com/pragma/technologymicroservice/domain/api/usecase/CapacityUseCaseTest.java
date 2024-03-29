package com.pragma.technologymicroservice.domain.api.usecase;

import com.pragma.technologymicroservice.domain.model.Capacity;
import com.pragma.technologymicroservice.domain.model.Technology;
import com.pragma.technologymicroservice.domain.spi.ICapacityPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
  @Test
  void testSaveCapacity() {
    List<Technology> technologies = new ArrayList<>();
    technologies.add(new Technology(1L, "Java", "Programming Language"));
    technologies.add(new Technology(2L, "Spring Boot", "Framework"));


    Capacity capacity = new Capacity(1L, "CapacityName", "CapacityDescription", technologies);

    capacityUseCase.saveCapacity(capacity);

    verify(capacityPersistencePort, times(1)).saveCapacity(capacity);
  }

  @Test
  void testGetAllCapacities(){
    List<Technology> technologies = new ArrayList<>();
    technologies.add(new Technology(1L, "Java", "Programming Language"));
    technologies.add(new Technology(2L, "Spring Boot", "Framework"));

    Capacity capacity = new Capacity(1L, "CapacityName", "CapacityDescription", technologies);

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



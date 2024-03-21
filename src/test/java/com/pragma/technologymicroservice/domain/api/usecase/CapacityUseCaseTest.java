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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


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

}



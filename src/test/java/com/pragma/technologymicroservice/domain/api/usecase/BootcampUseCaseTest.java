package com.pragma.technologymicroservice.domain.api.usecase;

import com.pragma.technologymicroservice.domain.model.Bootcamp;
import com.pragma.technologymicroservice.domain.model.Capacity;
import com.pragma.technologymicroservice.domain.model.Technology;
import com.pragma.technologymicroservice.domain.spi.IBootcampPersistencePort;
import com.pragma.technologymicroservice.utils.exception.RepeatCapInBootcampException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BootcampUseCaseTest {

  @Mock
  private IBootcampPersistencePort bootcampPersistencePort;
  private BootcampUseCase bootcampUseCase;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    bootcampUseCase = new BootcampUseCase(bootcampPersistencePort);
  }

  @Test
  void saveBootcamp() {

    Bootcamp bootcamp = new Bootcamp(2L,"Bootcamp","Description", any());

    bootcampUseCase .saveBootcamp(bootcamp);

    verify(bootcampPersistencePort,times(1)).saveBootcamp(bootcamp);

  }

  @Test
  void testRepeatCapacityInBootcampException(){

    List<Capacity> capacities = new ArrayList<>();
    capacities.add(new Capacity(1L,"Capacity","Description",List.of()));
    capacities.add(new Capacity(1L,"Capacity","Description",List.of()));

    Bootcamp bootcamp = new Bootcamp(2L,"Bootcamp","Description", capacities);

    doThrow(new RepeatCapInBootcampException()).when(bootcampPersistencePort).saveBootcamp(bootcamp);

    assertThrows(RepeatCapInBootcampException.class, () -> {
      bootcampUseCase.saveBootcamp(bootcamp);
    });

    verify(bootcampPersistencePort, never()).saveBootcamp(bootcamp);

  }
}
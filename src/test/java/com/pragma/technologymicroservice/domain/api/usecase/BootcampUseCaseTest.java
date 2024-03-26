package com.pragma.technologymicroservice.domain.api.usecase;

import com.pragma.technologymicroservice.domain.model.Bootcamp;
import com.pragma.technologymicroservice.domain.model.Capacity;
import com.pragma.technologymicroservice.domain.spi.IBootcampPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
}
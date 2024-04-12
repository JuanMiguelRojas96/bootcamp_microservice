package com.pragma.technologymicroservice.domain.api.usecase;

import com.pragma.technologymicroservice.domain.model.Bootcamp;
import com.pragma.technologymicroservice.domain.model.Version;
import com.pragma.technologymicroservice.domain.spi.IVersionPersistencePort;
import com.pragma.technologymicroservice.utils.exception.FinalDateBeforeInitialDateException;
import com.pragma.technologymicroservice.utils.exception.InitialDateBeforeCurrentDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class VersionUseCaseTest {

  @Mock
  private IVersionPersistencePort versionPersistencePort;

  @InjectMocks
  private VersionUseCase versionUseCase;

  @BeforeEach
  private void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  private Version createVersion() {

    LocalDate initialDate = LocalDate.of(2024, 6, 30);
    LocalDate finalDate = LocalDate.of(2024, 8, 30);

    return new Version(1L,initialDate,finalDate,60,new Bootcamp(1L,"Bootcamp","Description",new ArrayList<>()));
  }

  @Test
  void testSaveVersion() {

    Version version = createVersion();

    versionUseCase.saveVersion(version);

    verify(versionPersistencePort,times(1)).saveVersion(version);

  }

  @Test
  void testInitialDateBeforeCurrentDateException() {
    LocalDate initialDate = LocalDate.of(2020, 1, 1);
    LocalDate finalDate = LocalDate.of(2020, 2, 1);

    Version version = new Version(1L, initialDate, finalDate, 60, new Bootcamp(1L, "Bootcamp", "Description", new ArrayList<>()));

    assertThrows(InitialDateBeforeCurrentDateException.class, () -> versionUseCase.saveVersion(version));
    verify(versionPersistencePort, never()).saveVersion(version);
  }

  @Test
  void testFinalDateBeforeInitialDateException() {
    LocalDate initialDate = LocalDate.of(2024, 6, 30);
    LocalDate finalDate = LocalDate.of(2024, 6, 29);

    Version version = new Version(1L, initialDate, finalDate, 60, new Bootcamp(1L, "Bootcamp", "Description", new ArrayList<>()));

    assertThrows(FinalDateBeforeInitialDateException.class, () -> versionUseCase.saveVersion(version));
    verify(versionPersistencePort, never()).saveVersion(version);
  }

}
package com.pragma.technologymicroservice.domain.api.usecase;

import com.pragma.technologymicroservice.domain.api.ITechnologyServicePort;
import com.pragma.technologymicroservice.domain.exception.EmptyFieldException;
import com.pragma.technologymicroservice.domain.exception.MaxCharException;
import com.pragma.technologymicroservice.domain.model.Technology;
import com.pragma.technologymicroservice.domain.spi.ITechnologyPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

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
  void saveTechnology_ValidTechnology_ShouldSaveSuccessfully() {
    Technology technology = new Technology(1L, "Java", "Object-oriented programming language");
    technologyServicePort.saveTechnology(technology);
    verify(technologyPersistencePort).saveTechnology(technology);
  }

  @Test
  void saveTechnology_EmptyName_ShouldThrowEmptyFieldException() {
    Technology technology = new Technology(1L, "", "Description");
    assertThrows(EmptyFieldException.class, () -> technologyServicePort.saveTechnology(technology));
  }

  @Test
  void saveTechnology_EmptyDescription_ShouldThrowEmptyFieldException() {
    Technology technology = new Technology(1L, "Java", "");
    assertThrows(EmptyFieldException.class, () -> technologyServicePort.saveTechnology(technology));
  }

  @Test
  void saveTechnology_NameTooLong_ShouldThrowMaxCharException() {
    Technology technology = new Technology(1L, "JavaProgrammingLanguageJavaProgrammingLanguageJavaProgrammingLanguage", "Description");
    assertThrows(MaxCharException.class, () -> technologyServicePort.saveTechnology(technology));
  }

  @Test
  void saveTechnology_DescriptionTooLong_ShouldThrowMaxCharException() {
    Technology technology = new Technology(1L, "Java", "DescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescription");
    assertThrows(MaxCharException.class, () -> technologyServicePort.saveTechnology(technology));
  }

}
package com.pragma.technologymicroservice.adapters.driving.http.controller;

import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.pragma.technologymicroservice.domain.api.ITechnologyServicePort;
import com.pragma.technologymicroservice.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TechnologyRestControllerAdapterTest {

  @Mock
  private ITechnologyServicePort technologyServicePort;

  @Mock
  private ITechnologyRequestMapper technologyRequestMapper;

  private TechnologyRestControllerAdapter controller;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    controller = new TechnologyRestControllerAdapter(technologyServicePort, technologyRequestMapper);
  }

  @Test
  void addTechnology_ReturnsCreated() {

    AddTechnologyRequest request = new AddTechnologyRequest("Java", "Programming language");

    Technology mappedTechnology = new Technology(2L,"Java","Programing Language");
    when(technologyRequestMapper.addRequestToTechnology(request)).thenReturn(mappedTechnology);

    ResponseEntity<Void> responseEntity = controller.addTechnology(request);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    verify(technologyServicePort).saveTechnology(any(Technology.class));
  }

}
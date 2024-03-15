package com.pragma.technologymicroservice.adapters.driving.http.controller;

import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.pragma.technologymicroservice.domain.api.ITechnologyServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TechnologyRestControllerAdapterTest {

  @Mock
  private ITechnologyServicePort technologyServicePort;

  @Mock
  private ITechnologyRequestMapper technologyRequestMapper;

  @InjectMocks
  private TechnologyRestControllerAdapter controller;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void addTechnology_ValidRequest_ReturnsCreated() {
    AddTechnologyRequest request = new AddTechnologyRequest("Java","Programing Language");
    when(technologyRequestMapper.addRequestToTechnology(request)).thenReturn(any());

    ResponseEntity<Void> response = controller.addTechnology(request);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    verify(technologyServicePort, times(1)).saveTechnology(any());
  }
}
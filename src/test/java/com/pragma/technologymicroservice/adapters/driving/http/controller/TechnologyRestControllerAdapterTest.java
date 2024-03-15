package com.pragma.technologymicroservice.adapters.driving.http.controller;

import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.pragma.technologymicroservice.adapters.driving.http.dto.response.TechnologyResponse;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.ITechnologyResponseMapper;
import com.pragma.technologymicroservice.domain.api.ITechnologyServicePort;
import com.pragma.technologymicroservice.domain.api.usecase.TechnologyUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

 class TechnologyRestControllerAdapterTest {

  @Mock
  private ITechnologyServicePort technologyServicePort;

  @Mock
  private ITechnologyRequestMapper technologyRequestMapper;

  @Mock
  private ITechnologyResponseMapper technologyResponseMapper;


   @BeforeEach
   void setUp() {
     MockitoAnnotations.initMocks(this);
   }

  @Test
 void testAddTechnology() {
    TechnologyRestControllerAdapter controller = new TechnologyRestControllerAdapter(
        technologyServicePort, technologyRequestMapper, technologyResponseMapper);

    AddTechnologyRequest request = new AddTechnologyRequest("Java","Description");
    when(technologyRequestMapper.addRequestToTechnology(request)).thenReturn(any());

    ResponseEntity<Void> response = controller.addTechnology(request);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    verify(technologyServicePort, times(1)).saveTechnology(any());
  }

  @Test
 void testGetAllTechnologies() {
    TechnologyRestControllerAdapter controller = new TechnologyRestControllerAdapter(
        technologyServicePort, technologyRequestMapper, technologyResponseMapper);

    int page = 1;
    int size = 10;
    boolean ascending = true;

    List<TechnologyResponse> mockResponseList = Collections.singletonList(new TechnologyResponse(2L,"Java","Description"));
    when(technologyServicePort.getAllTechnologies(page, size, ascending)).thenReturn(Collections.emptyList());
    when(technologyResponseMapper.toTechnologyResponseList(Collections.emptyList())).thenReturn(mockResponseList);

    ResponseEntity<List<TechnologyResponse>> responseEntity = controller.getAllTechnologies(page, size, ascending);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(mockResponseList, responseEntity.getBody());
    verify(technologyServicePort, times(1)).getAllTechnologies(page, size, ascending);
    verify(technologyResponseMapper, times(1)).toTechnologyResponseList(Collections.emptyList());
  }
}
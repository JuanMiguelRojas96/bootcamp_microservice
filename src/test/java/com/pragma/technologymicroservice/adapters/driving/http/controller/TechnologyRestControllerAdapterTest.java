package com.pragma.technologymicroservice.adapters.driving.http.controller;

import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.pragma.technologymicroservice.adapters.driving.http.dto.response.TechnologyResponse;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.ITechnologyResponseMapper;
import com.pragma.technologymicroservice.domain.api.ITechnologyServicePort;
import com.pragma.technologymicroservice.domain.api.usecase.TechnologyUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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

  @InjectMocks
  private TechnologyRestControllerAdapter technologyRestControllerAdapter;


   @BeforeEach
   void setUp() {
     MockitoAnnotations.openMocks(this);
   }

  @Test
 void testAddTechnology() {

    AddTechnologyRequest request = new AddTechnologyRequest("Java","Description");
    when(technologyRequestMapper.addRequestToTechnology(request)).thenReturn(any());

    ResponseEntity<Void> response = technologyRestControllerAdapter.addTechnology(request);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    verify(technologyServicePort, times(1)).saveTechnology(any());
  }

  @Test
 void testGetAllTechnologies() {

    List<TechnologyResponse> mockResponseList = Collections.singletonList(new TechnologyResponse(2L,"Java","Description"));
    when(technologyServicePort.getAllTechnologies(1, 10, true)).thenReturn(Collections.emptyList());
    when(technologyResponseMapper.toTechnologyResponseList(Collections.emptyList())).thenReturn(mockResponseList);

    ResponseEntity<List<TechnologyResponse>> responseEntity = technologyRestControllerAdapter.getAllTechnologies(1, 10, true);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(mockResponseList, responseEntity.getBody());
    verify(technologyServicePort, times(1)).getAllTechnologies(1, 10, true);
    verify(technologyResponseMapper, times(1)).toTechnologyResponseList(Collections.emptyList());
  }
}
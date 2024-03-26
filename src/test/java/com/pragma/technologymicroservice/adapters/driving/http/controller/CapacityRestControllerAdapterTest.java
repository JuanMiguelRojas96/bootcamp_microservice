package com.pragma.technologymicroservice.adapters.driving.http.controller;

import com.pragma.technologymicroservice.adapters.driving.http.dto.TechnologyDto;
import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddCapacityRequest;
import com.pragma.technologymicroservice.adapters.driving.http.dto.response.CapacityResponse;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.ICapacityResponseMapper;
import com.pragma.technologymicroservice.domain.api.ICapacityServicePort;
import com.pragma.technologymicroservice.domain.model.Capacity;
import com.pragma.technologymicroservice.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CapacityRestControllerAdapterTest {

  @Mock
  private ICapacityServicePort capacityServicePort;
  @Mock
  private ICapacityRequestMapper capacityRequestMapper;

  @Mock
  private ICapacityResponseMapper capacityResponseMapper;

  private CapacityRestControllerAdapter capacityRestControllerAdapter;

  @BeforeEach
  void setUp(){
    MockitoAnnotations.openMocks(this);
    capacityRestControllerAdapter = new CapacityRestControllerAdapter(capacityServicePort,capacityRequestMapper,capacityResponseMapper);
  }

  @Test
  void testAddCapacity(){

    List<Technology> technologies = new ArrayList<>();
    technologies.add(new Technology(2L,"Java","Programing"));
    technologies.add(new Technology(3L,"Python","Programing"));
    technologies.add(new Technology(4L,"Angular","Programing"));

    Capacity capacity = new Capacity(1L,"Capacidad","Descripcion", technologies);
    AddCapacityRequest request = new AddCapacityRequest("Capacidad","Descripcion",technologies );
    when(capacityRequestMapper.addRequestToCapacity(request)).thenReturn(capacity);

    ResponseEntity<Void> response = capacityRestControllerAdapter.addCapacity(request);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    verify(capacityServicePort,times(1)).saveCapacity(capacity);

  }

  @Test
  void testGetAllCapacities(){

    List<TechnologyDto> technologies = new ArrayList<>();
    technologies.add(new TechnologyDto(2L,"Java"));
    technologies.add(new TechnologyDto(3L,"Python"));
    technologies.add(new TechnologyDto(4L,"Angular"));

    List<CapacityResponse> responseList = Collections.singletonList(new CapacityResponse(2L,"Capacity","description", technologies));

    when(capacityServicePort.getAllCapacities(1,10,true,true)).thenReturn(Collections.emptyList());
    when(capacityResponseMapper.toCapacityResponseList(Collections.emptyList())).thenReturn(responseList);

    ResponseEntity<List<CapacityResponse>> responseEntity = capacityRestControllerAdapter.getAllCapacities(1,10,true,true);

    assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    assertEquals(responseList,responseEntity.getBody());

    verify(capacityServicePort, times(1)).getAllCapacities(1,10,true,true);
    verify(capacityResponseMapper,times(1)).toCapacityResponseList(Collections.emptyList());

  }

}
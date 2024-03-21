package com.pragma.technologymicroservice.adapters.driving.http.controller;

import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddCapacityRequest;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.ICapacityRequestMapper;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CapacityRestControllerAdapterTest {

  @Mock
  private ICapacityServicePort capacityServicePort;
  @Mock
  private ICapacityRequestMapper capacityRequestMapper;

  @BeforeEach
  void setUp(){
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testAddCapacity(){

    List<Technology> technologies = new ArrayList<>();
    technologies.add(new Technology(2L,"Java","Programing"));
    technologies.add(new Technology(3L,"Python","Programing"));
    technologies.add(new Technology(4L,"Angular","Programing"));

    CapacityRestControllerAdapter controllerAdapter = new CapacityRestControllerAdapter(capacityServicePort,capacityRequestMapper);

    Capacity capacity = new Capacity(1L,"Capacidad","Descripcion", technologies);
    AddCapacityRequest request = new AddCapacityRequest("Capacidad","Descripcion",technologies );
    when(capacityRequestMapper.addRequestToCapacity(request)).thenReturn(capacity);

    ResponseEntity<Void> response = controllerAdapter.addCapacity(request);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    verify(capacityServicePort,times(1)).saveCapacity(capacity);

  }

}
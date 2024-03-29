package com.pragma.technologymicroservice.adapters.driving.http.controller;

import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddBootcampRequest;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.IBootcampResponseMapper;
import com.pragma.technologymicroservice.domain.api.IBootcampServicePort;
import com.pragma.technologymicroservice.domain.model.Bootcamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class BootcampRestControllerAdapterTest {

  @Mock
  private IBootcampServicePort bootcampServicePort;
  @Mock
  private IBootcampRequestMapper bootcampRequestMapper;
  @Mock
  private IBootcampResponseMapper bootcampResponseMapper;

  private BootcampRestControllerAdapter bootcampRestControllerAdapter;

  @BeforeEach
  void setUp(){
    MockitoAnnotations.openMocks(this);
    bootcampRestControllerAdapter = new BootcampRestControllerAdapter(bootcampServicePort,bootcampRequestMapper,bootcampResponseMapper);
  }

  @Test
  void testAddBootcamp(){

    Bootcamp bootcamp = new Bootcamp(1L,"Bootcamp", "Description", new ArrayList<>());
    AddBootcampRequest request = new AddBootcampRequest("Bootcamp","Description", new ArrayList<>());

    when(bootcampRequestMapper.addRequestToBootcamp(request)).thenReturn(bootcamp);

    ResponseEntity<Void> response = bootcampRestControllerAdapter.addBootCamp(request);

    assertEquals(HttpStatus.CREATED,response.getStatusCode());
    verify(bootcampServicePort, times(1)).saveBootcamp(bootcamp);

  }

}
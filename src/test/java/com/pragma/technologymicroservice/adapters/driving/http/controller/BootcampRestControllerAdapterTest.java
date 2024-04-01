package com.pragma.technologymicroservice.adapters.driving.http.controller;

import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddBootcampRequest;
import com.pragma.technologymicroservice.adapters.driving.http.dto.response.BootcampResponse;
import com.pragma.technologymicroservice.adapters.driving.http.dto.response.CapacityDto;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.IBootcampResponseMapper;
import com.pragma.technologymicroservice.domain.api.IBootcampServicePort;
import com.pragma.technologymicroservice.domain.model.Bootcamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class BootcampRestControllerAdapterTest {

  @Mock
  private IBootcampServicePort bootcampServicePort;
  @Mock
  private IBootcampRequestMapper bootcampRequestMapper;
  @Mock
  private IBootcampResponseMapper bootcampResponseMapper;

  @InjectMocks
  private BootcampRestControllerAdapter bootcampRestControllerAdapter;

  @BeforeEach
  void setUp(){
    MockitoAnnotations.openMocks(this);
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

  @Test
  void testGetAllBootcamps(){

    List<CapacityDto> capacities = new ArrayList<>();
    capacities.add(new CapacityDto(1L,"Capacidad",List.of()));
    capacities.add(new CapacityDto(2L,"Capacidad",List.of()));

    List<BootcampResponse> responseList = Collections.singletonList(new BootcampResponse(1L,"Bootcamp","descripcion",capacities));

    when(bootcampServicePort.getAllBootcamps(1,10,true,true)).thenReturn(Collections.emptyList());
    when(bootcampResponseMapper.toBootcampResponseList(Collections.emptyList())).thenReturn(responseList);

    ResponseEntity<List<BootcampResponse>> responseEntity = bootcampRestControllerAdapter.getAllBootcamps(1,10,true,true);

    assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    assertEquals(responseList,responseEntity.getBody());

    verify(bootcampServicePort, times(1)).getAllBootcamps(1,10,true,true);
    verify(bootcampResponseMapper,times(1)).toBootcampResponseList(Collections.emptyList());


  }

}
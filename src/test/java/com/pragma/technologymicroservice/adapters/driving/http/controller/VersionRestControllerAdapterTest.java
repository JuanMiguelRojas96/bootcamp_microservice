package com.pragma.technologymicroservice.adapters.driving.http.controller;

import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddVersionRequest;
import com.pragma.technologymicroservice.adapters.driving.http.dto.response.BootcampResponse;
import com.pragma.technologymicroservice.adapters.driving.http.dto.response.VersionResponse;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.IVersionRequestMapper;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.IVersionResponseMapper;
import com.pragma.technologymicroservice.domain.api.IVersionServicePort;
import com.pragma.technologymicroservice.domain.model.Bootcamp;
import com.pragma.technologymicroservice.domain.model.Version;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VersionRestControllerAdapterTest {

  @Mock
  private IVersionServicePort versionServicePort;
  @Mock
  private IVersionRequestMapper versionRequestMapper;
  @Mock
  private IVersionResponseMapper versionResposeMapper;

  @InjectMocks
  private VersionRestControllerAdapter versionRestControllerAdapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testAddVersion() {

    LocalDate initialDate = LocalDate.now();
    LocalDate finalDate = LocalDate.now();
    Integer cupos = 1;
    Bootcamp bootcamp = new Bootcamp(1L,"name","description",new ArrayList<>());

    Version version = new Version(1L,initialDate,finalDate,cupos,bootcamp);
    AddVersionRequest request = new AddVersionRequest(initialDate,finalDate,cupos,bootcamp);

    when(versionRequestMapper.addRequestToVersion(request)).thenReturn(version);

    ResponseEntity<Void> response = versionRestControllerAdapter.addVersion(request);

    assertEquals(HttpStatus.CREATED,response.getStatusCode());
    verify(versionServicePort, times(1)).saveVersion(version);

  }

  @Test
  void testGetAllVersions() {

    List<VersionResponse> responseList = Collections.singletonList(new VersionResponse(1L,LocalDate.now(),LocalDate.now(),60,
                                                                    new BootcampResponse(1L,"name","description",new ArrayList<>())));

    when(versionServicePort.getAllVersions(1,10,null,null,null,null)).thenReturn(Collections.emptyList());
    when(versionResposeMapper.toVersionResponseList(Collections.emptyList())).thenReturn(responseList);

    ResponseEntity<List<VersionResponse>> responseEntity = versionRestControllerAdapter.getAllVersions(1,10,null,null,null,null);

    assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    assertEquals(responseList,responseEntity.getBody());

    verify(versionServicePort, times(1)).getAllVersions(1,10,null,null,null,null);
    verify(versionResposeMapper,times(1)).toVersionResponseList(Collections.emptyList());

  }

}
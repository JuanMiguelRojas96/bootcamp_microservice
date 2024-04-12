package com.pragma.technologymicroservice.adapters.driving.http.controller;

import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddVersionRequest;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.IVersionRequestMapper;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VersionRestControllerAdapterTest {

  @Mock
  private IVersionServicePort versionServicePort;
  @Mock
  private IVersionRequestMapper versionRequestMapper;

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

}
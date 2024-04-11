package com.pragma.technologymicroservice.adapters.driving.http.controller;

import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddVersionRequest;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.IVersionRequestMapper;
import com.pragma.technologymicroservice.domain.api.IVersionServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/version")
@RequiredArgsConstructor
@Validated
public class VersionRestControllerAdapter {

  private final IVersionServicePort versionServicePort;
  private final IVersionRequestMapper versionRequestMapper;


  @PostMapping("/")
  public ResponseEntity<Void> addVersion (@Valid @RequestBody AddVersionRequest request) {

  versionServicePort.saveVersion(versionRequestMapper.addRequestToVersion(request));
  return ResponseEntity.status(HttpStatus.CREATED).build();

  }
}

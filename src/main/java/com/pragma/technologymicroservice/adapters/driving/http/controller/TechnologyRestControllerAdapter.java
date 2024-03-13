package com.pragma.technologymicroservice.adapters.driving.http.controller;


import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.pragma.technologymicroservice.domain.api.ITechnologyServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TechnologyRestControllerAdapter {
  private final ITechnologyServicePort technologyServicePort;
  private final ITechnologyRequestMapper technologyRequestMapper;


  @PostMapping("/")
  public ResponseEntity<Void> addTechnology(@RequestBody AddTechnologyRequest request){
    technologyServicePort.saveTechnology(technologyRequestMapper.addRequestToTechnology(request));
    return ResponseEntity.status(HttpStatus.CREATED).build();


  }



}

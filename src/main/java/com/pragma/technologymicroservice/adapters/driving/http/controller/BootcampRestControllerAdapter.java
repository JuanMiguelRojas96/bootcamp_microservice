package com.pragma.technologymicroservice.adapters.driving.http.controller;

import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddBootcampRequest;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.pragma.technologymicroservice.domain.api.IBootcampServicePort;
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
@RequestMapping("/bootcamp")
@RequiredArgsConstructor
@Validated
public class BootcampRestControllerAdapter {

  private final IBootcampServicePort bootcampServicePort;
  private final IBootcampRequestMapper bootcampRequestMapper;


  @PostMapping("/")
  public ResponseEntity<Void> addBootCamp(@Valid @RequestBody AddBootcampRequest request){
    bootcampServicePort.saveBootcamp(bootcampRequestMapper.addRequestToBootcamp(request));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}

package com.pragma.technologymicroservice.adapters.driving.http.controller;

import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddCapacityRequest;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.pragma.technologymicroservice.domain.api.ICapacityServicePort;
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
@RequestMapping("/capacity")
@RequiredArgsConstructor
@Validated
public class CapacityRestControllerAdapter {

  private final ICapacityServicePort capacityServicePort;
  private final ICapacityRequestMapper capacityRequestMapper;

  @PostMapping("/")
  public ResponseEntity<Void> addCapacity(@Valid @RequestBody AddCapacityRequest request){
    capacityServicePort.saveCapacity(capacityRequestMapper.addRequestToCapacity(request));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }


}

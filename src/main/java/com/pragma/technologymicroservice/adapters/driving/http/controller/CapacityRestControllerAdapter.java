package com.pragma.technologymicroservice.adapters.driving.http.controller;

import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddCapacityRequest;
import com.pragma.technologymicroservice.adapters.driving.http.dto.response.CapacityResponse;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.ICapacityResponseMapper;
import com.pragma.technologymicroservice.domain.api.ICapacityServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/capacity")
@RequiredArgsConstructor
@Validated
@Tag(name = "Capacity", description = "Endpoints para manejar capacidades")
public class CapacityRestControllerAdapter {

  private final ICapacityServicePort capacityServicePort;
  private final ICapacityRequestMapper capacityRequestMapper;
  private final ICapacityResponseMapper capacityResponseMapper;

  @PostMapping("/")
  @Operation(summary = "Endpoint to add a new capacity")
  public ResponseEntity<Void> addCapacity(@Valid @RequestBody AddCapacityRequest request){
    capacityServicePort.saveCapacity(capacityRequestMapper.addRequestToCapacity(request));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/")
  @Operation(summary = "Endpoint to get all capacities")
  public ResponseEntity<List<CapacityResponse>> getAllCapacities(@RequestParam Integer page, @RequestParam Integer size, @RequestParam(defaultValue = "true") boolean orderCapacity, @RequestParam(defaultValue = "true") boolean orderTech){
    return ResponseEntity.ok(capacityResponseMapper.toCapacityResponseList(capacityServicePort.getAllCapacities(page, size,orderCapacity,orderTech)));
  }
}

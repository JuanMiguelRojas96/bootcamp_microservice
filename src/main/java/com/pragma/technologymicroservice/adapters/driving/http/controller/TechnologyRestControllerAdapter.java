package com.pragma.technologymicroservice.adapters.driving.http.controller;


import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.pragma.technologymicroservice.adapters.driving.http.dto.response.TechnologyResponse;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.ITechnologyResponseMapper;
import com.pragma.technologymicroservice.configuration.security.jwt.JwtUtils;
import com.pragma.technologymicroservice.domain.api.ITechnologyServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/technology")
@RequiredArgsConstructor
@Validated
@Tag(name = "Tecnología", description = "Endpoints para manejar tecnologías")
@PreAuthorize("permitAll()")
@CrossOrigin(origins = "http://localhost:4200/")
public class TechnologyRestControllerAdapter {

  @Autowired
  private final JwtUtils jwtUtils;
  private final ITechnologyServicePort technologyServicePort;
  private final ITechnologyRequestMapper technologyRequestMapper;
  private final ITechnologyResponseMapper technologyResponseMapper;



  @PostMapping("/")
  @Operation(summary = "Endpoint to add a new technology")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public ResponseEntity<Void> addTechnology(@Valid @RequestBody AddTechnologyRequest request){
    technologyServicePort.saveTechnology(technologyRequestMapper.addRequestToTechnology(request));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/")
  @Operation(summary = "Endpoint to get all technologies")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public ResponseEntity<List<TechnologyResponse>> getAllTechnologies (@RequestParam Integer page,
                                                                      @RequestParam Integer size,
                                                                      @RequestParam(defaultValue = "true") boolean ascending){
    return ResponseEntity.ok(technologyResponseMapper.toTechnologyResponseList(technologyServicePort.getAllTechnologies(page,size,ascending)));
  }


}

package com.pragma.technologymicroservice.adapters.driving.http.controller;

import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddVersionRequest;
import com.pragma.technologymicroservice.adapters.driving.http.dto.response.VersionResponse;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.IVersionRequestMapper;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.IVersionResponseMapper;
import com.pragma.technologymicroservice.domain.api.IVersionServicePort;
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
@RequestMapping("/version")
@RequiredArgsConstructor
@Validated
@Tag(name = "Versión", description = "Endpoints para manejar versiones")
public class VersionRestControllerAdapter {

  private final IVersionServicePort versionServicePort;
  private final IVersionRequestMapper versionRequestMapper;
  private final IVersionResponseMapper versionResponseMapper;


  @PostMapping("/")
  @Operation(summary = "Endpoint to add a new version")
  public ResponseEntity<Void> addVersion (@Valid @RequestBody AddVersionRequest request) {

  versionServicePort.saveVersion(versionRequestMapper.addRequestToVersion(request));
  return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/")
  @Operation(summary = "Endpoint to get all versions")
  public ResponseEntity<List<VersionResponse>> getAllVersions(@RequestParam Integer page, @RequestParam Integer size,
                                                              @RequestParam(required = false) String orderBootcamp,
                                                              @RequestParam(required = false) String orderDate,
                                                              @RequestParam(required = false) String orderCupos,
                                                              @RequestParam(required = false) Long bootcampId) {
    return ResponseEntity.ok(versionResponseMapper.toVersionResponseList(versionServicePort.getAllVersions(page, size, orderBootcamp, orderDate, orderCupos, bootcampId)));

  }
}

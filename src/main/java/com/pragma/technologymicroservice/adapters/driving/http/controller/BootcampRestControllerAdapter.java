package com.pragma.technologymicroservice.adapters.driving.http.controller;

import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddBootcampRequest;
import com.pragma.technologymicroservice.adapters.driving.http.dto.response.BootcampResponse;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.pragma.technologymicroservice.adapters.driving.http.mapper.IBootcampResponseMapper;
import com.pragma.technologymicroservice.domain.api.IBootcampServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bootcamp")
@RequiredArgsConstructor
@Validated
public class BootcampRestControllerAdapter {

  private final IBootcampServicePort bootcampServicePort;
  private final IBootcampRequestMapper bootcampRequestMapper;
  private final IBootcampResponseMapper bootcampResponseMapper;


  @PostMapping("/")
  public ResponseEntity<Void> addBootCamp(@Valid @RequestBody AddBootcampRequest request){
    bootcampServicePort.saveBootcamp(bootcampRequestMapper.addRequestToBootcamp(request));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/")
  public ResponseEntity<List<BootcampResponse>> getAllBootcamps(@RequestParam Integer page, @RequestParam Integer size,@RequestParam(defaultValue = "true") boolean orderBootcamp,@RequestParam(defaultValue = "true") boolean orderCapacity ){
    return ResponseEntity.ok(bootcampResponseMapper.toBootcampResponseList(bootcampServicePort.getAllBootcamps(page, size, orderBootcamp, orderCapacity)));
  }
}

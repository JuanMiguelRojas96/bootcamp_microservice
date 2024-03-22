package com.pragma.technologymicroservice.adapters.driving.http.dto.response;

import com.pragma.technologymicroservice.adapters.driving.http.dto.TechnologyDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CapacityResponse {
  private final Long id;
  private final String name;
  private final String description;
  private final List<TechnologyDto> technologies;
}

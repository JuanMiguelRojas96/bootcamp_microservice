package com.pragma.technologymicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CapacityDto {
  private Long id;
  private String name;
  private final List<TechnologyDto> technologies;
}

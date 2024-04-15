package com.pragma.technologymicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class VersionResponse {

  private final Long id;
  private final LocalDate initialDate;
  private final LocalDate finalDate;
  private final Integer cupos;
  private final BootcampResponse bootcamp;
}

package com.pragma.technologymicroservice.adapters.driving.http.mapper;

import com.pragma.technologymicroservice.adapters.driving.http.dto.response.TechnologyResponse;
import com.pragma.technologymicroservice.domain.model.Technology;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITechnologyResponseMapper {
  TechnologyResponse toTechnologyResponse(Technology technology);
}

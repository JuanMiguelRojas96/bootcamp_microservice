package com.pragma.technologymicroservice.adapters.driving.http.mapper;

import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.pragma.technologymicroservice.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ITechnologyRequestMapper {

  @Mapping(target = "id", ignore = true )
  Technology addRequestToTechnology(AddTechnologyRequest addTechnologyRequest);
}

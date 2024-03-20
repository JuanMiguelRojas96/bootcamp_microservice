package com.pragma.technologymicroservice.adapters.driving.http.mapper;

import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddCapacityRequest;
import com.pragma.technologymicroservice.domain.model.Capacity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICapacityRequestMapper {

  @Mapping(target = "id", ignore = true)
  Capacity addRequestToCapacity(AddCapacityRequest addCapacityRequest);

}

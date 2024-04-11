package com.pragma.technologymicroservice.adapters.driving.http.mapper;

import com.pragma.technologymicroservice.adapters.driving.http.dto.request.AddVersionRequest;
import com.pragma.technologymicroservice.domain.model.Version;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IVersionRequestMapper {

  @Mapping(target = "id" , ignore = true)
  Version addRequestToVersion(AddVersionRequest addVersionRequest);
}

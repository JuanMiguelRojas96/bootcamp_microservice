package com.pragma.technologymicroservice.adapters.driving.http.mapper;

import com.pragma.technologymicroservice.adapters.driving.http.dto.response.VersionResponse;
import com.pragma.technologymicroservice.domain.model.Version;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IVersionResponseMapper {

  List<VersionResponse> toVersionResponseList(List<Version> versions);
}

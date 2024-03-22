package com.pragma.technologymicroservice.adapters.driving.http.mapper;

import com.pragma.technologymicroservice.adapters.driving.http.dto.response.TechnologyResponse;
import com.pragma.technologymicroservice.domain.model.Technology;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITechnologyResponseMapper {

  List<TechnologyResponse> toTechnologyResponseList(List<Technology> technologies);
}

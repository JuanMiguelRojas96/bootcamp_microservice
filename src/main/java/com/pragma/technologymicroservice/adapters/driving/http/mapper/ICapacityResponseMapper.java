package com.pragma.technologymicroservice.adapters.driving.http.mapper;

import com.pragma.technologymicroservice.adapters.driving.http.dto.response.CapacityResponse;
import com.pragma.technologymicroservice.domain.model.Capacity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICapacityResponseMapper {


  List<CapacityResponse> toCapacityResponseList(List<Capacity> capacities);
}

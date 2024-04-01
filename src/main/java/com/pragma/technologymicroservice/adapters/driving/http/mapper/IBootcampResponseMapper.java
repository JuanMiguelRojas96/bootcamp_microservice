package com.pragma.technologymicroservice.adapters.driving.http.mapper;

import com.pragma.technologymicroservice.adapters.driving.http.dto.response.BootcampResponse;
import com.pragma.technologymicroservice.domain.model.Bootcamp;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBootcampResponseMapper {

  List<BootcampResponse> toBootcampResponseList(List<Bootcamp> bootcamps);
}

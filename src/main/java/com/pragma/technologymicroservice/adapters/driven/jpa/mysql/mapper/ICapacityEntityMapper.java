package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.technologymicroservice.domain.model.Capacity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICapacityEntityMapper {

  Capacity toModel(CapacityEntity capacityEntity);


  CapacityEntity toEntity(Capacity capacity);

}

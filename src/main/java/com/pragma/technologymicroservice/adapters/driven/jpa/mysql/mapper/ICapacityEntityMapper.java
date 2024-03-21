package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.technologymicroservice.domain.model.Capacity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICapacityEntityMapper {

  Capacity toModel(CapacityEntity capacityEntity);

  CapacityEntity toEntity(Capacity capacity);

  List<Capacity> toModelList(List<CapacityEntity> capacityEntities);

}

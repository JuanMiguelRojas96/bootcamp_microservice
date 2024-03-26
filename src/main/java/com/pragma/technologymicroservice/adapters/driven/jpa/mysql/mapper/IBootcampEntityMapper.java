package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper;


import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.technologymicroservice.domain.model.Bootcamp;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBootcampEntityMapper {

  Bootcamp toModel(BootcampEntity bootcampEntity);

  BootcampEntity toEntity(Bootcamp bootcamp);
  List<Bootcamp> toModelList(List<BootcampEntity> bootcampEntities);
}

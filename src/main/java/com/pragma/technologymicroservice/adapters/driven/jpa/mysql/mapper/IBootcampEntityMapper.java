package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper;


import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.technologymicroservice.domain.model.Bootcamp;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBootcampEntityMapper {

  Bootcamp toModel(BootcampEntity bootcampEntity);

  BootcampEntity toEntity(Bootcamp bootcamp);
}

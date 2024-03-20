package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.technologymicroservice.domain.model.Technology;
import org.mapstruct.Mapper;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ITechnologyEntityMapper {
  Technology toModel(TechnologyEntity technologyEntity);
  TechnologyEntity toEntity(Technology technology);
  List<Technology> toModelList(List<TechnologyEntity> technologyEntities);

}

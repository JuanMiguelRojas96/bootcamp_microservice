package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.pragma.technologymicroservice.domain.model.Version;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IVersionEntityMapper {

  VersionEntity toEntity(Version version);

  Version toModel(VersionEntity versionEntity);
}

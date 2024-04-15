package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.pragma.technologymicroservice.domain.model.Version;
import com.pragma.technologymicroservice.domain.spi.IVersionPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;

@RequiredArgsConstructor
public class VersionAdapter implements IVersionPersistencePort {

  private final IVersionRepository versionRepository;
  private final IVersionEntityMapper versionEntityMapper;
  @Override
  public void saveVersion(Version version) {
    VersionEntity versionEntity = versionEntityMapper.toEntity(version);
    versionRepository.save(versionEntity);
  }

  @Override
  public List<Version> getAllVersions(Integer page, Integer size, String orderBootcamp,
                                      String orderDate, String orderCupos, Long bootcampId) {

    List<VersionEntity> versionEntities;

    Map<String, Sort.Direction> comparators = new HashMap<>();
    comparators.put("asc", Sort.Direction.ASC);
    comparators.put("desc", Sort.Direction.DESC);


    List<Sort.Order> sortingCriteria = new ArrayList<>();
    for (Map.Entry<String, Sort.Direction> entry : comparators.entrySet()) {
      if (entry.getKey().equals(orderBootcamp)) {
        sortingCriteria.add(new Sort.Order(entry.getValue(), "bootcamp.name"));
      } else if (entry.getKey().equals(orderDate)) {
        sortingCriteria.add(new Sort.Order(entry.getValue(), "initialDate"));
      } else if (entry.getKey().equals(orderCupos)) {
        sortingCriteria.add(new Sort.Order(entry.getValue(), "cupos"));
      }
    }


    Pageable pagination = PageRequest.of(page, size, Sort.by(sortingCriteria));

    if (bootcampId != null) {
      versionEntities = new ArrayList<>(versionRepository.findByBootcamp_Id(bootcampId, pagination).getContent());

    } else {
      versionEntities = new ArrayList<>(versionRepository.findAll(pagination).getContent());
    }

    return versionEntityMapper.toModelList(versionEntities);
  }
}

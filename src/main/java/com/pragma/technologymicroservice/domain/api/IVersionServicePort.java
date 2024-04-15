package com.pragma.technologymicroservice.domain.api;

import com.pragma.technologymicroservice.domain.model.Version;

import java.util.List;

public interface IVersionServicePort {

  void saveVersion(Version version);
  List<Version> getAllVersions(Integer page, Integer size, String orderBootcamp,
                               String orderDate, String orderCupos, Long bootcampId);
}

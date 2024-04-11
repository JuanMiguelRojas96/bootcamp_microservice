package com.pragma.technologymicroservice.domain.api;

import com.pragma.technologymicroservice.domain.model.Version;

public interface IVersionServicePort {

  void saveVersion(Version version);
}

package com.pragma.technologymicroservice.domain.spi;

import com.pragma.technologymicroservice.domain.model.Technology;

import java.util.List;


public interface ITechnologyPersistencePort {

  void saveTechnology(Technology technology);


  List<Technology> getAllTechnologies(Integer page, Integer size);
}

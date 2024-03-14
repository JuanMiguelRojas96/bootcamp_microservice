package com.pragma.technologymicroservice.domain.api;

import com.pragma.technologymicroservice.domain.model.Technology;

import java.util.List;

public interface ITechnologyServicePort {
    void saveTechnology(Technology technology);
    List<Technology> getAllTechnologies (Integer page, Integer size);

}

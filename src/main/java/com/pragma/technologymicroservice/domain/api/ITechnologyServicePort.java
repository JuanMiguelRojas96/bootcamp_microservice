package com.pragma.technologymicroservice.domain.api;

import com.pragma.technologymicroservice.domain.model.Technology;

public interface ITechnologyServicePort {
    void saveTechnology(Technology technology);

}

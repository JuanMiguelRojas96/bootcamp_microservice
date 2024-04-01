package com.pragma.technologymicroservice.domain.api;

import com.pragma.technologymicroservice.domain.model.Bootcamp;

import java.util.List;

public interface IBootcampServicePort {

  void saveBootcamp(Bootcamp bootcamp);

  List<Bootcamp> getAllBootcamps(Integer page, Integer size, boolean orderBootcamp, boolean orderCapacity);

}

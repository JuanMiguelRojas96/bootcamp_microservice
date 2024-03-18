package com.pragma.technologymicroservice.configuration;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.adapter.CapacityAdapter;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.adapter.TechnologyAdapter;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.technologymicroservice.domain.api.ICapacityServicePort;
import com.pragma.technologymicroservice.domain.api.ITechnologyServicePort;
import com.pragma.technologymicroservice.domain.api.usecase.CapacityUseCase;
import com.pragma.technologymicroservice.domain.api.usecase.TechnologyUseCase;
import com.pragma.technologymicroservice.domain.spi.ICapacityPersistencePort;
import com.pragma.technologymicroservice.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
  private final ITechnologyRepository technologyRepository;
  private final ITechnologyEntityMapper technologyEntityMapper;
  private final ICapacityRepository capacityRepository;
  private final ICapacityEntityMapper capacityEntityMapper;

  @Bean
  public ITechnologyPersistencePort technologyPersistencePort(){
    return new TechnologyAdapter(technologyRepository,technologyEntityMapper);
  }

  @Bean
  public ITechnologyServicePort technologyServicePort(){
    return new TechnologyUseCase(technologyPersistencePort());
  }

  @Bean
  public ICapacityPersistencePort capacityPersistencePort(){
    return new CapacityAdapter(capacityRepository,capacityEntityMapper);
  }

  @Bean
  public ICapacityServicePort capacityServicePort(){ return new CapacityUseCase(capacityPersistencePort());}
}

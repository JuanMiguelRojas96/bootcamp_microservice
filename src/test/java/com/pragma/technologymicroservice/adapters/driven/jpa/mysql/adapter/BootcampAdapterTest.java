package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.technologymicroservice.utils.exception.NoDataFoundException;
import com.pragma.technologymicroservice.utils.exception.RepeatCapInBootcampException;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.technologymicroservice.domain.model.Bootcamp;
import com.pragma.technologymicroservice.domain.model.Capacity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BootcampAdapterTest {

  @Mock
  private IBootcampRepository bootcampRepository;
  @Mock
  private IBootcampEntityMapper bootcampEntityMapper;
  @Mock
  private ICapacityRepository capacityRepository;

  private BootcampAdapter bootcampAdapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    bootcampAdapter = new BootcampAdapter(bootcampRepository,bootcampEntityMapper,capacityRepository);
  }

  @Test
  void testSaveBootcamp(){

    List<Capacity> capacities = new ArrayList<>();
    capacities.add(new Capacity(1L,"capacidad","descripcion",new ArrayList<>()));

    Bootcamp bootcamp = new Bootcamp(1L,"bootcamp","descripcion",capacities);

    CapacityEntity  existingCapacityEntity = new CapacityEntity(1L,"capacidad","descripcion",new ArrayList<>(),new ArrayList<>());

    List<CapacityEntity> capacityEntities = new ArrayList<>();
    capacityEntities.add(existingCapacityEntity);

    BootcampEntity bootcampEntity = new BootcampEntity(1L,"bootcamp","descripcion",capacityEntities);

    when(capacityRepository.findById(1L)).thenReturn(Optional.of(existingCapacityEntity));
    when(bootcampEntityMapper.toEntity(bootcamp)).thenReturn(bootcampEntity);

    assertDoesNotThrow(() -> bootcampAdapter.saveBootcamp(bootcamp));

    verify(bootcampRepository,times(1)).save(bootcampEntity);
  }

  @Test
  void testRepeatCapacityError(){

    List<Capacity> capacities = new ArrayList<>();
    capacities.add(new Capacity(1L,"capacidad","descripcion",List.of()));
    capacities.add(new Capacity(1L,"capacidad","descripcion",List.of()));

    Bootcamp bootcamp = new Bootcamp(1L,"bootcamp","descripcion",capacities);

    CapacityEntity  existingCapacityEntity = new CapacityEntity(1L,"capacidad","descripcion",List.of(), List.of());

    List<CapacityEntity> capacityEntities = new ArrayList<>();
    capacityEntities.add(existingCapacityEntity);

    when(capacityRepository.findById(1L)).thenReturn(Optional.of(existingCapacityEntity));

    assertThrows(RepeatCapInBootcampException.class, () -> bootcampAdapter.saveBootcamp(bootcamp));
  }

  @Test
  void testNotExistCapacityError(){

    List<Capacity> capacities = new ArrayList<>();
    capacities.add(new Capacity(1L, "Java", "Programing",List.of()));
    capacities.add(new Capacity(1L, "Java", "Programing",List.of()));


    Bootcamp bootcamp = new Bootcamp(1L,"Bootcamp","Descripcion",capacities);

    when(capacityRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(NoDataFoundException.class, () -> bootcampAdapter.saveBootcamp(bootcamp));

  }

}
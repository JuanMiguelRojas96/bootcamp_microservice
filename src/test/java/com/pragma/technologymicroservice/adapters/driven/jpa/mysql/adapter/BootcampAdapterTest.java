package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.technologymicroservice.utils.exception.BootcampAlreadyExistsException;
import com.pragma.technologymicroservice.utils.exception.NoDataFoundException;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.technologymicroservice.domain.model.Bootcamp;
import com.pragma.technologymicroservice.domain.model.Capacity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

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

  @InjectMocks
  private BootcampAdapter bootcampAdapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
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
  void testBootcampAlreadyExists(){

    Bootcamp bootcamp = new Bootcamp(1L,"bootcamp","Description",List.of());
    BootcampEntity bootcampEntity = new BootcampEntity(1L,"bootcamp","Description",List.of());

    when(bootcampRepository.findByName(bootcamp.getName())).thenReturn(Optional.of(bootcampEntity));

    assertThrows(BootcampAlreadyExistsException.class, () -> bootcampAdapter.saveBootcamp(bootcamp));

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

  @Test
  void testGetAllBootcamps(){
    int page = 0;
    int size = 10;
    boolean orderBootcamp = true;
    boolean orderCapacity = true;

    List<Capacity> capacities = new ArrayList<>();
    capacities.add(new Capacity(1L, "Java", "Programing",List.of()));
    capacities.add(new Capacity(1L, "Java", "Programing",List.of()));

    List<BootcampEntity> entities = new ArrayList<>();
    entities.add(new BootcampEntity(1L,"Bootcamp","Descripcion",List.of()));
    entities.add(new BootcampEntity(2L,"Bootcamp","Descripcion",List.of()));

    Page<BootcampEntity> pageOfEntities = new PageImpl<>(entities);
    when(bootcampRepository.findAll(any(PageRequest.class))).thenReturn(pageOfEntities);

    List<Bootcamp> expectedBootcamps = List.of(
        new Bootcamp(1L,"Bootcamp","Descripcion",capacities),
        new Bootcamp(2L,"Bootcamp","Descripcion",capacities)
    );
    when(bootcampEntityMapper.toModelList(entities)).thenReturn(expectedBootcamps);

    List<Bootcamp> actualBootcamps = bootcampAdapter.getAllBootcamps(page,size,orderBootcamp,orderCapacity);

    assertEquals(expectedBootcamps, actualBootcamps);

    verify(bootcampRepository).findAll(PageRequest.of(page,size));
    verify(bootcampEntityMapper).toModelList(entities);
  }

  @Test
  void testGetAllCapacitiesNoDataFound(){
    int page = 0;
    int size = 10;
    boolean orderBootcamp = true;
    boolean orderCapacity = true;

    Page<BootcampEntity> emptyPageOfEntities = new PageImpl<>(new ArrayList<>());

    when(bootcampRepository.findAll(any(PageRequest.class))).thenReturn(emptyPageOfEntities);
    when(bootcampEntityMapper.toModelList(any())).thenReturn(new ArrayList<>());

    assertThrows(NoDataFoundException.class, () -> bootcampAdapter.getAllBootcamps(page,size,orderBootcamp,orderCapacity));

    verify(bootcampRepository).findAll(PageRequest.of(page,size));
    verify(bootcampEntityMapper,never()).toModelList(any());
  }
}
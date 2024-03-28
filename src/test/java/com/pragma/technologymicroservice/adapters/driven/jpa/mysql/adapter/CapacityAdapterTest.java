package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.technologymicroservice.utils.exception.NoDataFoundException;
import com.pragma.technologymicroservice.utils.exception.RepeatTechInCapacityException;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.technologymicroservice.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.technologymicroservice.domain.model.Capacity;
import com.pragma.technologymicroservice.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

class CapacityAdapterTest {


  @Mock
  private ICapacityRepository capacityRepository;
  @Mock
  private ITechnologyRepository technologyRepository;
  @Mock
  private ICapacityEntityMapper capacityEntityMapper;
  private CapacityAdapter capacityAdapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    capacityAdapter = new CapacityAdapter(capacityRepository,capacityEntityMapper,technologyRepository);
  }

  @Test
  void testSaveCapacity(){

    List<Technology> technologies = new ArrayList<>();
    technologies.add(new Technology(1L,"Java","Programing"));

    Capacity capacity = new Capacity(1L,"capacity","Description",technologies);

    TechnologyEntity existingTechnologyEntity = new TechnologyEntity();
    existingTechnologyEntity.setId(1L);
    existingTechnologyEntity.setName("Java");
    existingTechnologyEntity.setDescription("Programming");

    List<TechnologyEntity> technologyEntities = new ArrayList<>();
    technologyEntities.add(existingTechnologyEntity);

    CapacityEntity capacityEntity = new CapacityEntity(1L,"capacity","Description",technologyEntities,null);

    when(technologyRepository.findById(1L)).thenReturn(Optional.of(existingTechnologyEntity));
    when(capacityEntityMapper.toEntity(capacity)).thenReturn(capacityEntity);

    assertDoesNotThrow(() -> capacityAdapter.saveCapacity(capacity));

    verify(capacityRepository,times(1)).save(capacityEntity);

  }

  @Test
  void testRepeatCapacityError() {

    List<Technology> technologies = new ArrayList<>();
    technologies.add(new Technology(1L, "Java", "Programing"));
    technologies.add(new Technology(1L, "Java", "Programing"));

    Capacity capacity = new Capacity(1L, "capacity", "Description", technologies);

    TechnologyEntity existingTechnologyEntity = new TechnologyEntity();
    existingTechnologyEntity.setId(1L);
    existingTechnologyEntity.setName("Java");
    existingTechnologyEntity.setDescription("Programming");

    List<TechnologyEntity> technologyEntities = new ArrayList<>();
    technologyEntities.add(existingTechnologyEntity);

    when(technologyRepository.findById(1L)).thenReturn(Optional.of(existingTechnologyEntity));

    assertThrows(RepeatTechInCapacityException.class, () -> capacityAdapter.saveCapacity(capacity));
  }

  @Test
  void testNotExistTechnologyError() {

    List<Technology> technologies = new ArrayList<>();
    technologies.add(new Technology(1L, "Java", "Programing"));
    technologies.add(new Technology(1L, "Java", "Programing"));

    Capacity capacity = new Capacity(1L, "capacity", "Description", technologies);

    when(technologyRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(NoDataFoundException.class, () -> capacityAdapter.saveCapacity(capacity));
  }

  @Test
  void testGetAllCapacities() {

    int page = 0;
    int size = 10;
    boolean orderCapacity = true;
    boolean orderTech = true;

    List<Technology> technologies = new ArrayList<>();
    technologies.add(new Technology(1L, "Java", "Programing"));
    technologies.add(new Technology(1L, "Java", "Programing"));


    List<CapacityEntity> entities = new ArrayList<>();
    entities.add(new CapacityEntity(1L, "Capacity 1","description", List.of(new TechnologyEntity()),null));
    entities.add(new CapacityEntity(2L, "Capacity 2","description", List.of(new TechnologyEntity(), new TechnologyEntity()),null));

    Page<CapacityEntity> pageOfEntities = new PageImpl<>(entities);
    when(capacityRepository.findAll(any(PageRequest.class))).thenReturn(pageOfEntities);

    List<Capacity> expectedCapacities = List.of(
        new Capacity(1L, "Capacity 1","description", technologies),
        new Capacity(2L, "Capacity 2","description", technologies)
    );
    when(capacityEntityMapper.toModelList(entities)).thenReturn(expectedCapacities);

    List<Capacity> actualCapacities = capacityAdapter.getAllCapacities(page, size, orderCapacity, orderTech);

    assertEquals(expectedCapacities, actualCapacities);

    verify(capacityRepository).findAll(PageRequest.of(page, size));
    verify(capacityEntityMapper).toModelList(entities);
  }

  @Test
  void testGetAllCapacitiesNoDataFound() {

    int page = 0;
    int size = 10;
    boolean orderCapacity = true;
    boolean orderTech = true;

    Page<CapacityEntity> emptyPageOfEntities = new PageImpl<>(new ArrayList<>());
    when(capacityRepository.findAll(any(PageRequest.class))).thenReturn(emptyPageOfEntities);

    when(capacityEntityMapper.toModelList(any())).thenReturn(new ArrayList<>());

    assertThrows(NoDataFoundException.class, () -> capacityAdapter.getAllCapacities(page, size, orderCapacity, orderTech));

    verify(capacityRepository).findAll(PageRequest.of(page, size));
    verify(capacityEntityMapper, never()).toModelList(any());
  }





}
package com.pragma.technologymicroservice.domain.model;

import java.util.List;

public class Bootcamp {
  private Long id;
  private String name;
  private String description;
  private List<Capacity> capacities;

  public Bootcamp(Long id, String name, String description, List<Capacity> capacities) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.capacities = capacities;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Capacity> getCapacities() {
    return capacities;
  }

  public void setCapacities(List<Capacity> capacities) {
    this.capacities = capacities;
  }
}

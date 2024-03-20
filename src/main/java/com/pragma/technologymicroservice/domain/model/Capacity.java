package com.pragma.technologymicroservice.domain.model;


import java.util.List;

public class Capacity {
  private final Long id;
  private String name;
  private String description;
  private List<Technology> technologies;

  public Capacity(Long id, String name, String description, List<Technology> technologies) {
    this.id = id;
    this.name= name;
    this.description = description;
    this.technologies = technologies;

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

  public List<Technology> getTechnologies() {
    return technologies;
  }

  public void setTechnologies(List<Technology> technologies) {
    this.technologies = technologies;
  }
}

package com.pragma.technologymicroservice.domain.model;

import com.pragma.technologymicroservice.domain.util.DomainConstants;

import static java.util.Objects.requireNonNull;

public class Technology {
  
  private final Long id;
  private String name;
  private String description;


  public Technology(Long id, String name, String description) {
    this.id = id;
    this.name = requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
    this.description = requireNonNull(description,DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

/*  public Long setId( ) {
    this.id = id;
  }*/

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }


}

package com.pragma.technologymicroservice.domain.model;

import java.time.LocalDate;

public class Version {

  private Long id;
  private LocalDate initialDate;
  private LocalDate finalDate;
  private Integer cupos;
  private Bootcamp bootcamp;

  public Version(Long id, LocalDate initialDate,LocalDate finalDate , Integer cupos, Bootcamp bootcamp) {
    this.id = id;
    this.initialDate = initialDate;
    this.finalDate = finalDate;
    this.cupos = cupos;
    this.bootcamp = bootcamp;
  }

  public Long getId() {
    return id;
  }

  public LocalDate getInitialDate() {
    return initialDate;
  }

  public void setInitialDate(LocalDate initialDate) {
    this.initialDate = initialDate;
  }

  public LocalDate getFinalDate() {
    return finalDate;
  }

  public void setFinalDate(LocalDate finalDate) {
    this.finalDate = finalDate;
  }

  public Integer getCupos() {
    return cupos;
  }

  public void setCupos(Integer cupos) {
    this.cupos = cupos;
  }

  public Bootcamp getBootcamp() {
    return bootcamp;
  }

  public void setBootcamp(Bootcamp bootcamp) {
    this.bootcamp = bootcamp;
  }
}



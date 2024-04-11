package com.pragma.technologymicroservice.domain.model;

import java.util.Date;

public class Version {

  private Long id;
  private Date fecha_Inicio;
  private Date fecha_Fin;
  private Long cupos;
  private Bootcamp bootcamp;

  public Version(Long id, Date fecha_Inicio,Date fecha_Fin , Long cupos, Bootcamp bootcamp) {
    this.id = id;
    this.fecha_Inicio = fecha_Inicio;
    this.fecha_Fin = fecha_Fin;
    this.cupos = cupos;
    this.bootcamp = bootcamp;
  }

  public Long getId() {
    return id;
  }


  public Date getFecha_Inicio() {
    return fecha_Inicio;
  }

  public void setFecha_Inicio(Date fecha_Inicio) {
    this.fecha_Inicio = fecha_Inicio;
  }

  public Date getFecha_Fin() {
    return fecha_Fin;
  }

  public void setFecha_Fin(Date fecha_Fin) {
    this.fecha_Fin = fecha_Fin;
  }

  public Long getCupos() {
    return cupos;
  }

  public void setCupos(Long cupos) {
    this.cupos = cupos;
  }

  public Bootcamp getBootcamp() {
    return bootcamp;
  }

  public void setBootcamp(Bootcamp bootcamp) {
    this.bootcamp = bootcamp;
  }
}



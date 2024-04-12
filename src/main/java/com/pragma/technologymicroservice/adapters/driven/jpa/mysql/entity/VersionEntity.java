package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "version")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VersionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private LocalDate initialDate;

  @Column
  private LocalDate finalDate;

  @Column
  private Integer cupos;

  @ManyToOne
  @JoinColumn(name = "bootcamp_id")
  private BootcampEntity bootcamp;

}

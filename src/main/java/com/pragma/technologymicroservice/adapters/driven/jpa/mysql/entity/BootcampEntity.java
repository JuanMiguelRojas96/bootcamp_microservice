package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bootcamp")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BootcampEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(length = 50)
  private String name;
  @Column(length = 90)
  private String description;

  @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @JoinTable(
      name = "bootcamp_capacity",
      joinColumns = @JoinColumn(name = "bootcamp_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "capacity_id", referencedColumnName = "id"),
      uniqueConstraints = @UniqueConstraint(columnNames = {"bootcamp_id", "capacity_id"})
  )
  private List<CapacityEntity> capacities;
}

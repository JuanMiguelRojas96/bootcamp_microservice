package com.pragma.technologymicroservice.adapters.driven.jpa.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "capacity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CapacityEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(length = 50)
  private String name;
  @Column(length = 90)
  private String description;

  @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @JoinTable(
      name = "capacity_technology",
      joinColumns = @JoinColumn(name = "capacity_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "technology_id", referencedColumnName = "id"),
      uniqueConstraints = @UniqueConstraint(columnNames = {"capacity_id", "technology_id"})
  )
  private List<TechnologyEntity> technologies;

  @ManyToMany(mappedBy = "capacities")
  private List<BootcampEntity> bootcamps;
}

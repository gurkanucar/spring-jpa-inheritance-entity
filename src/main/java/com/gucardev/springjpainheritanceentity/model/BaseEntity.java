package com.gucardev.springjpainheritanceentity.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Setter
@Getter
public abstract class BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
}

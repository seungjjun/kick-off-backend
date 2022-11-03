package com.junstudio.kickoff.models;

import com.junstudio.kickoff.dtos.CategoryDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category {
  @Id
  @GeneratedValue
  @Column(name = "category_id")
  private Long id;

  private String name;

  public Category() {
  }

  public Category(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long id() {
    return id;
  }

  public String name() {
    return name;
  }

  public CategoryDto toDto() {
    return new CategoryDto(id, name);
  }
}

package com.junstudio.kickoff.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Grade {
  @Id @GeneratedValue
  @Column(name = "grade_id")
  private Long id;

  private String name;

  @JsonManagedReference
  @OneToMany(mappedBy = "grade")
  private List<User> users = new ArrayList<>();

  public Grade() {
  }

  public Grade(Long id, String name, List<User> users) {
    this.id = id;
    this.name = name;
    this.users = users;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<User> getUsers() {
    return users;
  }
}

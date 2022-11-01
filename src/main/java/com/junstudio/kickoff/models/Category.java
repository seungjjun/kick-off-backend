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
public class Category {
  @Id @GeneratedValue
  @Column(name = "category_id")
  private Long id;

  private String name;

  @JsonManagedReference
  @OneToMany(mappedBy = "category")
  private List<Post> posts = new ArrayList<>();

  public Category() {
  }

  public Category(Long id, String name, List<Post> posts) {
    this.id = id;
    this.name = name;
    this.posts = posts;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<Post> getPosts() {
    return posts;
  }
}

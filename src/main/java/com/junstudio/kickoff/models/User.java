package com.junstudio.kickoff.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.junstudio.kickoff.dtos.UserDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PERSON")
public class User {
  @Id
  @GeneratedValue
  @Column(name = "user_id")
  private Long id;

  private String identification;

  private String encodedPassword;

  private String name;

  private String profileImage;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "grade_id")
  private Grade grade;

  @JsonManagedReference
  @OneToMany(mappedBy = "user")
  private List<Post> posts = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "user")
  private List<Comment> comments = new ArrayList<>();

  public User() {
  }

  public User(Long id, String identification, String encodedPassword,
              String name, String profileImage, Grade grade,
              List<Post> posts, List<Comment> comments) {
    this.id = id;
    this.identification = identification;
    this.encodedPassword = encodedPassword;
    this.name = name;
    this.profileImage = profileImage;
    this.grade = grade;
    this.posts = posts;
    this.comments = comments;
  }

  public Long getId() {
    return id;
  }

  public String getIdentification() {
    return identification;
  }

  public String getEncodedPassword() {
    return encodedPassword;
  }

  public String getName() {
    return name;
  }

  public String getProfileImage() {
    return profileImage;
  }

  public Grade getGrade() {
    return grade;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public UserDto toDto() {
    return new UserDto(id, identification, name, profileImage);
  }
}

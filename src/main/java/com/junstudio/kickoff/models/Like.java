package com.junstudio.kickoff.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LIKES")
public class Like {
  @Id @GeneratedValue
  @Column(name = "like_id")
  private Long id;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "post_id")
  private Post post;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Like() {
  }

  public Like(Long id, Post post, User user) {
    this.id = id;
    this.post = post;
    this.user = user;
  }

  public Like(Post post, User user) {
    this.post = post;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public Post getPost() {
    return post;
  }

  public User getUser() {
    return user;
  }
}

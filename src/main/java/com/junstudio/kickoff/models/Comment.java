package com.junstudio.kickoff.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Comment {
  @Id @GeneratedValue
  @Column(name = "comment_id")
  private Long id;

  private String content;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "post_id")
  private Post post;

  @CreationTimestamp
  private LocalDateTime commentDate;

  public Comment() {
  }

  public Comment(Long id, String content, User user, Post post, LocalDateTime commentDate) {
    this.id = id;
    this.content = content;
    this.user = user;
    this.post = post;
    this.commentDate = commentDate;
  }

  public Long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

  public User getUser() {
    return user;
  }

  public Post getPost() {
    return post;
  }

  public LocalDateTime getCommentDate() {
    return commentDate;
  }
}

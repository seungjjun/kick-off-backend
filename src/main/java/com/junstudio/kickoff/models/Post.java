package com.junstudio.kickoff.models;

import com.junstudio.kickoff.dtos.PostDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post {
  @GeneratedValue
  @Id
  private Long id;

  private String title;

  private String author;

  private String category;

  private Long commentNumber;

  private Long likeNumber;

  public Post() {
  }

  public Post(Long id, String title, String author,
              String category, Long commentNumber, Long likeNumber) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.category = category;
    this.commentNumber = commentNumber;
    this.likeNumber = likeNumber;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public String getCategory() {
    return category;
  }

  public Long getCommentNumber() {
    return commentNumber;
  }

  public Long getLikeNumber() {
    return likeNumber;
  }

  public PostDto toDto() {
    return new PostDto(id, title, author, category, commentNumber, likeNumber);
  }
}

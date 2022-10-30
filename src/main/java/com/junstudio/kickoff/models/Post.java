package com.junstudio.kickoff.models;

import com.junstudio.kickoff.dtos.PostDetailDto;
import com.junstudio.kickoff.dtos.PostDto;
import com.junstudio.kickoff.dtos.WrittenDto;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Post {
  @GeneratedValue
  @Id
  private Long id;

  private String title;

  private String content;

  private String author;

  private Long hit;

  private String category;

  private Long commentNumber;

  private Long likeNumber;

  @Column(name = "imageUrl", length = 2048)
  private String imageUrl;

  @CreationTimestamp
  private LocalDateTime createdAt;

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

  public Post(Long id, String title, String content,
              String author, String category, Long hit, LocalDateTime createdAt,
              String imageUrl) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.author = author;
    this.category = category;
    this.hit = hit;
    this.createdAt = createdAt;
    this.imageUrl = imageUrl;
  }

  public Post(String title, String content, String category, Long hit, String imageUrl) {
    this.title = title;
    this.content = content;
    this.category = category;
    this.hit = hit;
    this.imageUrl = imageUrl;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
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

  public Long getHit() {
    return hit;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public PostDto toDto() {
    return new PostDto(id, title, author,
        category, commentNumber, likeNumber,
        hit, createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), imageUrl);
  }

  public PostDetailDto toDetailDto() {
    return new PostDetailDto(id, title, content,
        author, category, hit,
        createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), imageUrl);
  }

  public void updateHit(Long hit) {
    this.hit = hit + 1L;
  }

  public WrittenDto writtenDto() {
    return new WrittenDto(id);
  }
}

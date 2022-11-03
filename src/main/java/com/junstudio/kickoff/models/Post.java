package com.junstudio.kickoff.models;

import com.junstudio.kickoff.dtos.PostDetailDto;
import com.junstudio.kickoff.dtos.PostDto;
import com.junstudio.kickoff.dtos.PostWrittenDto;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
public class Post {
  @GeneratedValue
  @Id
  @Column(name = "post_id")
  private Long id;

  private Long userId;

  private Long categoryId;

  private String title;

  private String content;

  private Long hit;

  @Column(name = "imageUrl", length = 2048)
  private String imageUrl;

  @CreationTimestamp
  private LocalDateTime createdAt;

  public Post() {
  }

  public Post(Long id, Long userId, Long categoryId, String title,
              String content, Long hit, String imageUrl,
              LocalDateTime createdAt) {
    this.id = id;
    this.userId = userId;
    this.categoryId = categoryId;
    this.title = title;
    this.content = content;
    this.hit = hit;
    this.imageUrl = imageUrl;
    this.createdAt = createdAt;
  }

  public Post(String title, String content, Long hit,
              String imageUrl, Long userId, Long categoryId) {
    this.title = title;
    this.content = content;
    this.hit = hit;
    this.imageUrl = imageUrl;
    this.userId = userId;
    this.categoryId = categoryId;
  }

  public Long id() {
    return id;
  }

  public Long userId() {
    return userId;
  }

  public Long categoryId() {
    return categoryId;
  }


  public String title() {
    return title;
  }

  public String content() {
    return content;
  }

  public Long hit() {
    return hit;
  }

  public String imageUrl() {
    return imageUrl;
  }

  public LocalDateTime createdAt() {
    return createdAt;
  }

  public PostDto toDto() {
    return new PostDto(id, title, categoryId, userId, hit,
        createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), imageUrl);
  }


  public void updateHit(Long hit) {
    this.hit = hit + 1L;
  }

  public PostWrittenDto postWrittenDto() {
    return new PostWrittenDto(id);
  }

  public PostDetailDto toDetailDto(User user, Category category, List<Like> likes) {
    return new PostDetailDto(id, title, content, hit, likes, user, category,
        createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), imageUrl);
  }
}

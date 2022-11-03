package com.junstudio.kickoff.dtos;

public class PostDto {
  private final Long id;

  private final String title;

  private final Long categoryId;

  private final Long userId;

  private final Long hit;

  private final String createdAt;

  private final String imageUrl;

  public PostDto(Long id, String title, Long categoryId,
                 Long userId, Long hit, String createdAt, String imageUrl) {
    this.id = id;
    this.title = title;
    this.categoryId = categoryId;
    this.userId = userId;
    this.hit = hit;
    this.createdAt = createdAt;
    this.imageUrl = imageUrl;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public Long getUserId() {
    return userId;
  }

  public Long getHit() {
    return hit;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getImageUrl() {
    return imageUrl;
  }
}

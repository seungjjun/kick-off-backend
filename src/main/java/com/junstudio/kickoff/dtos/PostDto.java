package com.junstudio.kickoff.dtos;

public class PostDto {
  private final Long id;

  private final String title;

  private final String author;

  private final String category;

  private final Long commentNumber;

  private final Long likeNumber;

  private final Long hit;

  private final String createdAt;

  private final String imageUrl;

  public PostDto(Long id, String title, String author,
                 String category, Long commentNumber, Long likeNumber,
                 Long hit, String createdAt, String imageUrl) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.category = category;
    this.commentNumber = commentNumber;
    this.likeNumber = likeNumber;
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

  public String getCreatedAt() {
    return createdAt;
  }

  public String getImageUrl() {
    return imageUrl;
  }
}

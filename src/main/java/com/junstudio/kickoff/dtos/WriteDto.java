package com.junstudio.kickoff.dtos;

public class WriteDto {
  private final Long id;

  private final String title;

  private final String content;

  private final String category;

  private final String imageUrl;

  public WriteDto(Long id, String title, String content, String category, String imageUrl) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.category = category;
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

  public String getCategory() {
    return category;
  }

  public String getImageUrl() {
    return imageUrl;
  }
}

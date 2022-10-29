package com.junstudio.kickoff.dtos;

import javax.persistence.Column;

public class PostDetailDto {
  private final Long id;

  private final String title;

  private final String content;

  private final String author;

  private final String category;

  private final Long hit;

  public PostDetailDto(Long id, String title, String content,
                       String author, String category, Long hit) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.author = author;
    this.category = category;
    this.hit = hit;
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

  public Long getHit() {
    return hit;
  }

}

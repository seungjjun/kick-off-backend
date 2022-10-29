package com.junstudio.kickoff.dtos;

import javax.validation.constraints.NotBlank;

public class WriteDto {
  private Long id;

  @NotBlank
  private String title;

  private String content;

  private String category;

  public WriteDto(Long id, String title, String content, String category) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.category = category;
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
}

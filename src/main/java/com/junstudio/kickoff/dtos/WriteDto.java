package com.junstudio.kickoff.dtos;

import javax.validation.constraints.NotBlank;

public class WriteDto {
  @NotBlank
  private String title;

  private String content;

  private String category;

  public WriteDto(String title, String content, String category) {
    this.title = title;
    this.content = content;
    this.category = category;
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

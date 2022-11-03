package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.Category;
import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class PostDetailDto {
  private final Long id;

  private final String title;

  private final String content;

  private final CategoryDto category;

  private final UserDto user;

  private final Long hit;

  private final List<LikeDto> likes;

  private final String createdAt;

  private final String imageUrl;

  public PostDetailDto(Long id, String title, String content, Long hit,
                       List<Like> likes, User user, Category category,
                       String createdAt, String imageUrl) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.category = category.toDto();
    this.user = user.toDto();
    this.hit = hit;
    this.likes = likes.stream().map(Like::toLikeDto).collect(Collectors.toList());
    this.createdAt = createdAt;
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

  public CategoryDto getCategory() {
    return category;
  }

  public UserDto getUser() {
    return user;
  }

  public Long getHit() {
    return hit;
  }

  public List<LikeDto> getLikes() {
    return likes;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getImageUrl() {
    return imageUrl;
  }
}

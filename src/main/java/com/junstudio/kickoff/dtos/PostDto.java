package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.Category;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.models.User;

import java.util.List;

public class PostDto {
  private final Long id;

  private final String title;

  private final List<Comment> comments;

  private final Category category;

  private final User user;

  private final Long hit;

  private final List<Like> likes;

  private final String createdAt;

  private final String imageUrl;

  public PostDto(Long id, String title, List<Comment> comments,
                 Category category, User user, Long hit,
                 List<Like> likes, String createdAt, String imageUrl) {
    this.id = id;
    this.title = title;
    this.comments = comments;
    this.category = category;
    this.user = user;
    this.hit = hit;
    this.likes = likes;
    this.createdAt = createdAt;
    this.imageUrl = imageUrl;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public Category getCategory() {
    return category;
  }

  public User getUser() {
    return user;
  }

  public Long getHit() {
    return hit;
  }

  public List<Like> getLikes() {
    return likes;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getImageUrl() {
    return imageUrl;
  }
}

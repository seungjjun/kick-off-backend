package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.Grade;

public class UserDto {
  private final Long id;

  private final String userId;

  private final String name;

  private final String profileImage;

  public UserDto(Long id, String userId, String name, String profileImage) {
    this.id = id;
    this.userId = userId;
    this.name = name;
    this.profileImage = profileImage;
  }

  public Long getId() {
    return id;
  }

  public String getUserId() {
    return userId;
  }

  public String getName() {
    return name;
  }

  public String getProfileImage() {
    return profileImage;
  }
}

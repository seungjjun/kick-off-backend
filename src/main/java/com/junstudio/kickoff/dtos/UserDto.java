package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.Grade;

public class UserDto {
  private final Long id;

  private final String identification;

  private final String name;

  private final String profileImage;

  public UserDto(Long id, String identification, String name, String profileImage) {
    this.id = id;
    this.identification = identification;
    this.name = name;
    this.profileImage = profileImage;
  }

  public Long getId() {
    return id;
  }

  public String getIdentification() {
    return identification;
  }

  public String getName() {
    return name;
  }

  public String getProfileImage() {
    return profileImage;
  }
}

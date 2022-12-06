package com.junstudio.kickoff.dtos;

public class UserDto {
    private final Long id;

    private final String identification;

    private final String name;

    private final String profileImage;

    private final String grade;

    private final boolean isMyToken;

    private final String createdAt;

    public UserDto(
        Long id,
        String identification,
        String name,
        String profileImage,
        String grade,
        boolean isMyToken,
        String createdAt
    ) {
        this.id = id;
        this.identification = identification;
        this.name = name;
        this.profileImage = profileImage;
        this.grade = grade;
        this.isMyToken = isMyToken;
        this.createdAt = createdAt;
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

    public String getGrade() {
        return grade;
    }

    public boolean getIsMyToken() {
        return isMyToken;
    }

    public boolean isMyToken() {
        return isMyToken;
    }
}

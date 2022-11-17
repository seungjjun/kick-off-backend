package com.junstudio.kickoff.dtos;

public class LoginResultDto {
    private final String accessToken;

    private final String name;

    private final String profileImage;

    private final String gradeName;

    public LoginResultDto(String accessToken, String name, String profileImage, String gradeName) {
        this.accessToken = accessToken;
        this.name = name;
        this.profileImage = profileImage;
        this.gradeName = gradeName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getName() {
        return name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getGradeName() {
        return gradeName;
    }
}

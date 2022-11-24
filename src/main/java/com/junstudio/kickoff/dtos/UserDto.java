package com.junstudio.kickoff.dtos;

public class UserDto {
    private final Long id;

    private final String identification;

    private final String name;

    private final String profileImage;

    private final boolean isMyToken;

    public UserDto(
        Long id,
        String identification,
        String name,
        String profileImage,
        boolean isMyToken
    ) {

        this.id = id;
        this.identification = identification;
        this.name = name;
        this.profileImage = profileImage;
        this.isMyToken = isMyToken;
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

    public boolean getIsMyToken() {
        return isMyToken;
    }
}

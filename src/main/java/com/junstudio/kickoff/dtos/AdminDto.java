package com.junstudio.kickoff.dtos;

public class AdminDto {
    private final String identification;
    private final String name;
    private final String profileImage;

    public AdminDto(String identification, String name, String profileImage) {

        this.identification = identification;
        this.name = name;
        this.profileImage = profileImage;
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

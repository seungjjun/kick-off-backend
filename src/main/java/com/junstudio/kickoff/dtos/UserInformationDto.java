package com.junstudio.kickoff.dtos;

public class UserInformationDto {
    private final UsersDto usersDto;

    public UserInformationDto(UsersDto usersDto) {
        this.usersDto = usersDto;
    }

    public UsersDto getMyInformation() {
        return usersDto;
    }
}

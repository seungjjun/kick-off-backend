package com.junstudio.kickoff.dtos;

public class FoundUserDto {
    private final UsersDto usersDto;

    public FoundUserDto(UsersDto usersDto) {
        this.usersDto = usersDto;
    }

    public UsersDto getFoundUser() {
        return usersDto;
    }
}

package com.junstudio.kickoff.dtos;

import java.util.List;

public class UsersDto {

    private final List<UserDto> users;

    public UsersDto(List<UserDto> users) {
        this.users = users;
    }

    public List<UserDto> getUsers() {
        return users;
    }
}

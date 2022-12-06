package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.User;

import java.util.List;

public class TodaySignupUsersDto {
    private final List<User> users;

    public TodaySignupUsersDto(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}

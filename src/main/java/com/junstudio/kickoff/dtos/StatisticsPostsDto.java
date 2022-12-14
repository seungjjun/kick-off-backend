package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class StatisticsPostsDto {
    private final List<StatisticsPostDto> posts;
    private final List<UserDto> users;

    public
    StatisticsPostsDto(List<StatisticsPostDto> posts, List<User> users) {
        this.posts = posts;
        this.users = users.stream().map(User::toDto).collect(Collectors.toList());
    }

    public List<StatisticsPostDto> getPosts() {
        return posts;
    }

    public List<UserDto> getUsers() {
        return users;
    }
}

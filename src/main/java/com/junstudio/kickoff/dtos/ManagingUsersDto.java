package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class ManagingUsersDto {
    private final List<UserDto> users;
    private final List<Long> postNumbers;
    private final List<Long> commentNumbers;

    public ManagingUsersDto(List<User> users,
                            List<Long> postNumbers,
                            List<Long> commentNumbers) {

        this.users = users.stream().map(User::toDto).collect(Collectors.toList());
        this.postNumbers = postNumbers;
        this.commentNumbers = commentNumbers;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public List<Long> getPostNumbers() {
        return postNumbers;
    }

    public List<Long> getCommentNumbers() {
        return commentNumbers;
    }
}

package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.User;

public class SearchedUserDto {
    private final UserDto user;
    private final Long postNumber;
    private final Long commentNumber;

    public SearchedUserDto(User user, Long postNumber, Long commentNumber) {

        this.user = user.toDto();
        this.postNumber = postNumber;
        this.commentNumber = commentNumber;
    }

    public UserDto getUser() {
        return user;
    }

    public Long getPostNumber() {
        return postNumber;
    }

    public Long getCommentNumber() {
        return commentNumber;
    }
}

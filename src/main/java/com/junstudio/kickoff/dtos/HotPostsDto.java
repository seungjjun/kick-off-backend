package com.junstudio.kickoff.dtos;

import java.util.List;

public class HotPostsDto {
    private final List<PostDto> posts;
    private final List<Integer> commentNumbers;
    private final List<BoardDto> hotBoards;
    private final List<UserDto> hotUsers;


    public HotPostsDto(List<PostDto> posts,
                       List<Integer> commentNumbers,
                       List<BoardDto> hotBoards,
                       List<UserDto> hotUsers) {
        this.posts = posts;
        this.commentNumbers = commentNumbers;
        this.hotBoards = hotBoards;
        this.hotUsers = hotUsers;
    }


    public List<PostDto> getPosts() {
        return posts;
    }

    public List<Integer> getCommentNumber() {
        return commentNumbers;
    }

    public List<BoardDto> getBoards() {
        return hotBoards;
    }

    public List<UserDto> getUsers() {
        return hotUsers;
    }
}

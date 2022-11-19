package com.junstudio.kickoff.dtos;

import java.util.List;

public class CreatePostsDto {
    private final List<PostDto> posts;
    private final List<CommentDto> comments;
    private final List<ReCommentDto> reComments;
    private final List<LikeDto> likes;
    private final List<UserDto> users;
    private final List<BoardDto> boards;

    public CreatePostsDto(List<PostDto> posts,
                          List<CommentDto> comments,
                          List<ReCommentDto> reComments,
                          List<LikeDto> likes,
                          List<UserDto> users,
                          List<BoardDto> boards) {
        this.posts = posts;
        this.comments = comments;
        this.reComments = reComments;
        this.likes = likes;
        this.users = users;
        this.boards = boards;
    }

    public List<PostDto> getPosts() {
        return posts;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public List<ReCommentDto> getReComments() {
        return reComments;
    }

    public List<LikeDto> getLikes() {
        return likes;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public List<BoardDto> getBoards() {
        return boards;
    }
}

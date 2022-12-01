package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.User;

import java.util.List;

public class UsersDto {
    private List<UserDto> users;
    private UserDto user;
    private List<PostDto> posts;
    private List<CommentDto> comments;
    private List<ReCommentDto> reCommentDtos;
    private List<PostDto> likedPosts;
    private ManagingUsersDto managingUsersDto;

    public UsersDto(List<UserDto> users) {
        this.users = users;
    }

    public UsersDto(User user,
                    List<PostDto> posts,
                    List<CommentDto> comments,
                    List<ReCommentDto> reCommentDtos,
                    List<PostDto> likedPosts) {

        this.user = user.toDto();
        this.posts = posts;
        this.comments = comments;
        this.reCommentDtos = reCommentDtos;
        this.likedPosts = likedPosts;
    }

    public UsersDto(ManagingUsersDto managingUsersDto) {
        this.managingUsersDto = managingUsersDto;
    }

    public ManagingUsersDto getMembers() {
        return managingUsersDto;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public UserDto getUser() {
        return user;
    }

    public List<PostDto> getPosts() {
        return posts;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public List<ReCommentDto> getRecomments() {
        return reCommentDtos;
    }

    public List<PostDto> getLikedPosts() {
        return likedPosts;
    }
}

package com.junstudio.kickoff.dtos;

import java.util.List;

public class PostsDto {
  private final List<PostDto> posts;

  private final List<CommentDto> comments;

  private final List<ReCommentDto> recomments;

  private final List<LikeDto> likes;

  private final List<UserDto> users;

  private final List<CategoryDto> categories;

  public PostsDto(List<PostDto> posts, List<CommentDto> comments,
                  List<ReCommentDto> recomments, List<LikeDto> likes,
                  List<UserDto> users, List<CategoryDto> categories) {
    this.posts = posts;
    this.comments = comments;
    this.recomments = recomments;
    this.likes = likes;
    this.users = users;
    this.categories = categories;
  }

  public List<PostDto> getPosts() {
    return posts;
  }

  public List<CommentDto> getComments() {
    return comments;
  }

  public List<ReCommentDto> getRecomments() {
    return recomments;
  }

  public List<LikeDto> getLikes() {
    return likes;
  }

  public List<UserDto> getUsers() {
    return users;
  }

  public List<CategoryDto> getCategories() {
    return categories;
  }
}

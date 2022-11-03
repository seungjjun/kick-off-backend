package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class CommentDto {
  private final Long id;

  private final String content;

  private final Long userId;

  private final Long postId;

  private final String commentDate;

  public CommentDto(Long id, String content, Long userId, Long postId,
                    String commentDate) {
    this.id = id;
    this.content = content;
    this.userId = userId;
    this.postId = postId;
    this.commentDate = commentDate;
  }

  public Long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

  public Long getUserId() {
    return userId;
  }

  public Long getPostId() {
    return postId;
  }

  public String getCommentDate() {
    return commentDate;
  }
}
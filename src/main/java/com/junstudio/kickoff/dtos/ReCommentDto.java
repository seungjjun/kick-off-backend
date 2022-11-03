package com.junstudio.kickoff.dtos;

public class ReCommentDto {
  private final Long id;

  private final String content;

  private final Long commentId;

  private final Long postId;

  private final Long userId;

  public ReCommentDto(Long id, String content, Long commentId, Long postId, Long userId) {
    this.id = id;
    this.content = content;
    this.commentId = commentId;
    this.postId = postId;
    this.userId = userId;
  }

  public Long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

  public Long getCommentId() {
    return commentId;
  }

  public Long getPostId() {
    return postId;
  }

  public Long getUserId() {
    return userId;
  }
}

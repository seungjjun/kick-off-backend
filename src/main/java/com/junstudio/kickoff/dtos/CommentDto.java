package com.junstudio.kickoff.dtos;

public class CommentDto {
  private final Long id;

  private final String content;

  private final Long userId;

  private final Long postId;

  private final boolean isDeleted;

  private final String commentDate;

  public CommentDto(Long id, String content, Long userId, Long postId, boolean isDeleted,
                    String commentDate) {
    this.id = id;
    this.content = content;
    this.userId = userId;
    this.postId = postId;
    this.isDeleted = isDeleted;
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

  public boolean isDeleted() {
    return isDeleted;
  }

  public String getCommentDate() {
    return commentDate;
  }
}

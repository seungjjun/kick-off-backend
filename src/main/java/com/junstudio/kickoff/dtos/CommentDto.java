package com.junstudio.kickoff.dtos;

public class CommentDto {
  private Long id;

  private String content;

  private Long userId;

  private Long postId;

  private Long receiverId;

  private boolean isDeleted;

  private String commentDate;

  public CommentDto() {
  }

  public CommentDto(Long id, String content, Long userId, Long postId, boolean isDeleted,
                    String commentDate) {
    this.id = id;
    this.content = content;
    this.userId = userId;
    this.postId = postId;
    this.isDeleted = isDeleted;
    this.commentDate = commentDate;
  }

  public CommentDto(Long id, String content, Long userId, Long postId, Long receiverId, boolean isDeleted, String commentDate) {
    this.id = id;
    this.content = content;
    this.userId = userId;
    this.postId = postId;
    this.receiverId = receiverId;
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

  public Long getReceiverId() {
    return receiverId;
  }
}

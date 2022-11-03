package com.junstudio.kickoff.dtos;

import java.util.List;

public class CommentsDto {

  private final List<CommentDto> comments;

  private final List<ReCommentDto> recomments;

  public CommentsDto(List<CommentDto> comments, List<ReCommentDto> recomments) {
    this.comments = comments;
    this.recomments = recomments;
  }

  public List<CommentDto> getComments() {
    return comments;
  }

  public List<ReCommentDto> getRecomments() {
    return recomments;
  }
}

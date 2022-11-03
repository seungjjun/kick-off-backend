package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.CommentDto;
import com.junstudio.kickoff.dtos.CommentsDto;
import com.junstudio.kickoff.dtos.ReCommentDto;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.services.CommentService;
import com.junstudio.kickoff.services.RecommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommentController {
  private final CommentService commentService;
  private final RecommentService recommentService;

  public CommentController(CommentService commentService, RecommentService recommentService) {
    this.commentService = commentService;
    this.recommentService = recommentService;
  }

  @GetMapping("/posts/{postId}/comments")
  private CommentsDto comments(
      @PathVariable Long postId
  ) {
    List<CommentDto> comments = commentService.findComment(postId)
        .stream().map(Comment::toDto)
        .collect(Collectors.toList());

    List<ReCommentDto> recomments = recommentService.findReComment(postId)
        .stream().map(Recomment::toDto)
        .collect(Collectors.toList());

    return new CommentsDto(comments, recomments);
  }

  @PostMapping("/comment")
  @ResponseStatus(HttpStatus.CREATED)
  private String comment(
      @RequestBody CommentDto commentDto
  ) {
    commentService.createComment(
        commentDto.getContent(),
        commentDto.getUserId(),
        commentDto.getPostId());
    return "ok";
  }
}

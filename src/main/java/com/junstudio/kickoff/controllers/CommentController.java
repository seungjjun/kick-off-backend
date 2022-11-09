package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.CommentDto;
import com.junstudio.kickoff.dtos.CommentsDto;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.services.CreateCommentService;
import com.junstudio.kickoff.services.GetCommentService;
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
    private final GetCommentService getCommentService;
    private final CreateCommentService createCommentService;

    public CommentController(GetCommentService getCommentService,
                             CreateCommentService createCommentService) {
        this.getCommentService = getCommentService;
        this.createCommentService = createCommentService;
    }

    @GetMapping("/comments")
    private CommentsDto comments() {
        List<CommentDto> comments = getCommentService.comments()
            .stream().map(Comment::toDto)
            .collect(Collectors.toList());

        return new CommentsDto(comments);
    }

    @GetMapping("/posts/{postId}/comments")
    private CommentsDto comments(
        @PathVariable Long postId
    ) {
        List<CommentDto> comments = getCommentService.findComment(postId)
            .stream().map(Comment::toDto)
            .collect(Collectors.toList());
        return new CommentsDto(comments);
    }

    @PostMapping("/comment")
    @ResponseStatus(HttpStatus.CREATED)
    private String comment(
        @RequestBody CommentDto commentDto
    ) {
        createCommentService.createComment(
            commentDto.getContent(),
            commentDto.getUserId(),
            commentDto.getPostId());
        return "ok";
    }
}

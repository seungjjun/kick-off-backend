package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.CommentDto;
import com.junstudio.kickoff.dtos.CommentsDto;
import com.junstudio.kickoff.dtos.SelectedCommentDto;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.services.CreateCommentService;
import com.junstudio.kickoff.services.DeleteCommentService;
import com.junstudio.kickoff.services.GetCommentService;
import com.junstudio.kickoff.services.PatchCommentService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class CommentController {
    private final GetCommentService getCommentService;
    private final CreateCommentService createCommentService;
    private final PatchCommentService patchCommentService;
    private final DeleteCommentService deleteCommentService;

    public CommentController(GetCommentService getCommentService,
                             CreateCommentService createCommentService,
                             PatchCommentService patchCommentService,
                             DeleteCommentService deleteCommentService) {
        this.getCommentService = getCommentService;
        this.createCommentService = createCommentService;
        this.patchCommentService = patchCommentService;
        this.deleteCommentService = deleteCommentService;
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
        @PathVariable Long postId,
        @PageableDefault(sort = "id", direction = Sort.Direction.ASC, value = 5) Pageable pageable
    ) {
        return getCommentService.findComment(postId, pageable);
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

    @PatchMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void update(
        @RequestBody CommentDto commentDto,
        @PathVariable Long commentId
    ) {
        patchCommentService.patchComment(commentId, commentDto.getContent());
    }

    @DeleteMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable Long commentId) {
        deleteCommentService.deleteComment(commentId);
    }

    @DeleteMapping("/comments")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteSelectedComments(
        @RequestBody SelectedCommentDto selectedCommentDto
        ) {
        deleteCommentService.deleteComments(selectedCommentDto.getCommentId());
    }
}

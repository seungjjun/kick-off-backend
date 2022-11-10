package com.junstudio.kickoff.dtos;

import java.util.List;

public class CommentsDto {
    private final List<CommentDto> comments;

    private CommentPageDto commentPageDto;

    public CommentsDto(List<CommentDto> comments) {
        this.comments = comments;
    }

    public CommentsDto(List<CommentDto> comments, CommentPageDto commentPageDto) {
        this.comments = comments;
        this.commentPageDto = commentPageDto;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public CommentPageDto getPage() {
        return commentPageDto;
    }
}

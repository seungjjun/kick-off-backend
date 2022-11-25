package com.junstudio.kickoff.dtos;

import java.util.List;

public class SelectedCommentDto {
    public List<Long> commentId;

    public SelectedCommentDto() {
    }

    public SelectedCommentDto(List<Long> commentsId) {
        this.commentId = commentsId;
    }

    public List<Long> getCommentId() {
        return commentId;
    }
}

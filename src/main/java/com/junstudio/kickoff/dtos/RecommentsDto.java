package com.junstudio.kickoff.dtos;

import java.util.List;

public class RecommentsDto {
    private final List<ReCommentDto> recomments;

    public RecommentsDto(List<ReCommentDto> recomments) {
        this.recomments = recomments;
    }

    public List<ReCommentDto> getRecomments() {
        return recomments;
    }
}

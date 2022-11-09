package com.junstudio.kickoff.dtos;

import java.util.List;

public class LikesDto {
    private final List<LikeDto> likes;

    public LikesDto(List<LikeDto> likes) {
        this.likes = likes;
    }

    public List<LikeDto> getLikes() {
        return likes;
    }
}

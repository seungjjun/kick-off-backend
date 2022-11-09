package com.junstudio.kickoff.dtos;

public class LikeDto {
    private final Long id;

    private final Long postId;

    private final Long userId;

    public LikeDto(Long id, Long postId, Long userId) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Long getPostId() {
        return postId;
    }

    public Long getUserId() {
        return userId;
    }
}

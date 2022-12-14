package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.PostInformation;

public class PostDto {
    private final Long id;

    private final PostInformationDto postInformation;

    private final Long boardId;

    private final Long userId;

    private final Long hit;

    private final String createdAt;

    private final String imageUrl;

    public PostDto(Long id, PostInformation postInformation, Long boardId,
                   Long userId, Long hit, String createdAt, String imageUrl) {
        this.id = id;
        this.postInformation = postInformation.toDto();
        this.boardId = boardId;
        this.userId = userId;
        this.hit = hit;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public PostInformationDto getPostInformation() {
        return postInformation;
    }

    public Long getBoardId() {
        return boardId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getHit() {
        return hit;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public PostDto toDto() {
        return new PostDto(id, new PostInformation(postInformation.getTitle(),
            postInformation.getContent()), boardId, userId, hit, createdAt, imageUrl);
    }
}

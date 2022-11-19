package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.PostInformation;
import com.junstudio.kickoff.models.UserId;

public class PostDto {
    private final Long id;

    private final PostInformationDto postInformation;

    private final Long boardId;

    private final UserId userId;

    private final Long hit;

    private final Long likeNumber;

    private final String createdAt;

    private final String imageUrl;

    public PostDto(Long id, PostInformation postInformation, Long boardId,
                   UserId userId, Long hit, Long likeNumber, String createdAt, String imageUrl) {
        this.id = id;
        this.postInformation = postInformation.toDto();
        this.boardId = boardId;
        this.userId = userId;
        this.hit = hit;
        this.likeNumber = likeNumber;
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

    public UserId getUserId() {
        return userId;
    }

    public Long getHit() {
        return hit;
    }

    public Long getLikeNumber() {
        return likeNumber;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public PostDto toDto() {
        return new PostDto(id, new PostInformation(postInformation.getTitle(),
            postInformation.getContent()), boardId, userId, hit, likeNumber,
            createdAt, imageUrl);
    }
}

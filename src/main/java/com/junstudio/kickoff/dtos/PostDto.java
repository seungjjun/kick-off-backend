package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.PostInformation;
import com.junstudio.kickoff.models.UserId;

public class PostDto {
    private final Long id;

    private final PostInformationDto postInformation;

    private final Long categoryId;

    private final UserId userId;

    private final Long hit;

    private final String createdAt;

    private final String imageUrl;

    public PostDto(Long id, PostInformation postInformation, Long categoryId,
                   UserId userId, Long hit, String createdAt, String imageUrl) {
        this.id = id;
        this.postInformation = postInformation.toDto();
        this.categoryId = categoryId;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public UserId getUserId() {
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
        return new PostDto(id, new PostInformation(postInformation.getTitle(), postInformation.getContent()),
            categoryId, userId, hit, createdAt, imageUrl);
    }
}

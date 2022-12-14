package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.PostInformation;

public class StatisticsPostDto {
    private final Long id;

    private final PostInformationDto postInformation;

    private final Long userId;

    private final Long hit;

    private final String createdAt;

    public StatisticsPostDto(Long id,
                             PostInformation postInformation,
                             Long userId,
                             Long hit,
                             String createdAt) {
        this.id = id;
        this.postInformation = postInformation.toDto();
        this.userId = userId;
        this.hit = hit;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public PostInformationDto getPostInformation() {
        return postInformation;
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
}

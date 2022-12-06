package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.PostInformation;
import com.junstudio.kickoff.models.UserId;

public class StatisticsPostDto {
    private final Long id;

    private final PostInformationDto postInformation;

    private final UserId userId;

    private final Long hit;

    private final String createdAt;

    public StatisticsPostDto(Long id,
                             PostInformation postInformation,
                             UserId userId,
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

    public UserId getUserId() {
        return userId;
    }

    public Long getHit() {
        return hit;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}

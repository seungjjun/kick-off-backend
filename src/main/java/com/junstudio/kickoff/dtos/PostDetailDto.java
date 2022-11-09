package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.Category;
import com.junstudio.kickoff.models.PostInformation;

public class PostDetailDto {
    private final Long id;

    private final PostInformation postInformation;

    private final CategoryDto category;

    private final Long hit;

    private final String createdAt;

    private final String imageUrl;

    public PostDetailDto(Long id, PostInformation postInformation, Long hit,
                         Category category, String createdAt, String imageUrl) {
        this.id = id;
        this.postInformation = postInformation;
        this.category = category.toDto();
        this.hit = hit;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public PostInformation getPostInformation() {
        return postInformation;
    }

    public CategoryDto getCategory() {
        return category;
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
}

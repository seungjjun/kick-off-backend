package com.junstudio.kickoff.dtos;

public class PostWriteDto {
    private final Long id;

    private final String title;

    private final String content;

    private final String imageUrl;

    private final Long userId;

    private final Long categoryId;

    public PostWriteDto(Long id, String title, String content,
                        String imageUrl, Long userId, Long categoryId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}

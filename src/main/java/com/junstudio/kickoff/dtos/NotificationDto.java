package com.junstudio.kickoff.dtos;

public class NotificationDto {
    private final Long id;

    private final String sender;

    private final String content;

    private final Long postId;

    private final boolean isRead;

    private final String createdAt;

    public NotificationDto(Long id, String sender, String content, Long postId, boolean isRead, String createdAt) {
        this.id = id;
        this.sender = sender;
        this.content = content;
        this.postId = postId;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public Long getPostId() {
        return postId;
    }

    public boolean isRead() {
        return isRead;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}

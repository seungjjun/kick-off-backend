package com.junstudio.kickoff.models;

import com.junstudio.kickoff.dtos.NotificationDto;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Notification {
    @Id
    @GeneratedValue
    @Column(name = "notification_id")
    private Long id;

    private Long receiverId;

    private Long postId;

    private String sender;

    private String content;

    @Column(columnDefinition = "boolean default false")
    private boolean isRead;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Notification() {
    }

    public Notification(Long id,
                        Long receiverId,
                        String sender,
                        String content,
                        boolean isRead,
                        LocalDateTime createdAt
    ) {
        this.id = id;
        this.receiverId = receiverId;
        this.sender = sender;
        this.content = content;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }

    public Notification(Long receiverId, Long postId, String sender, String content) {
        this.receiverId = receiverId;
        this.postId = postId;
        this.sender = sender;
        this.content = content;
        this.isRead = false;
    }

    public Long id() {
        return id;
    }

    public Long receiverId() {
        return receiverId;
    }

    public Long postId() {
        return postId;
    }

    public String sender() {
        return sender;
    }

    public String content() {
        return content;
    }

    public boolean isRead() {
        return isRead;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public NotificationDto toDto() {
        return new NotificationDto(id, sender, content, postId, isRead);
    }

    public void read() {
        isRead = true;
    }

    public static Notification fake() {
        return new Notification(
            1L,
            1L,
            "pikachu",
            "Million volt",
            false,
            LocalDateTime.now());
    }
}

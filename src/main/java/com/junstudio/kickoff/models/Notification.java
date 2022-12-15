package com.junstudio.kickoff.models;

import com.junstudio.kickoff.dtos.ResponseDto;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Notification {
    @Id
    @GeneratedValue
    @Column(name = "notification_id")
    private Long id;

    private Long receiverId;

    private String sender;

    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Notification() {
    }

    public Notification(Long id, Long receiverId, String sender, String content) {
        this.id = id;
        this.receiverId = receiverId;
        this.sender = sender;
        this.content = content;
    }

    public Notification(String sender, String content) {;
        this.sender = sender;
        this.content = content;
    }

    public Long id() {
        return id;
    }

    public Long receiverId() {
        return receiverId;
    }

    public String sender() {
        return sender;
    }

    public String content() {
        return content;
    }

    public ResponseDto toDto() {
        return new ResponseDto(content, sender);
    }
}

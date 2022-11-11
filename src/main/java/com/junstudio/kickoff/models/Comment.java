package com.junstudio.kickoff.models;

import com.junstudio.kickoff.dtos.CommentDto;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String content;

    private Long userId;

    private Long postId;

    @CreationTimestamp
    private LocalDateTime commentDate;

    public Comment() {
    }

    public Comment(Long id, String content, Long userId, Long postId,
                   LocalDateTime commentDate) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.postId = postId;
        this.commentDate = commentDate;
    }

    public Comment(String content, Long userId, Long postId) {
        this.content = content;
        this.userId = userId;
        this.postId = postId;
    }

    public Long id() {
        return id;
    }

    public String content() {
        return content;
    }

    public Long userId() {
        return userId;
    }

    public Long postId() {
        return postId;
    }

    public LocalDateTime commentDate() {
        return commentDate;
    }

    public CommentDto toDto() {
        return new CommentDto(id, content, userId, postId,
            commentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    public static Comment fake() {
        return new Comment(1L, "reply", 1L, 1L, LocalDateTime.now());
    }
}

package com.junstudio.kickoff.models;

import com.junstudio.kickoff.dtos.ReCommentDto;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Recomment {
    @Id
    @GeneratedValue
    @Column(name = "recomment_id")
    private Long id;

    private Long commentId;

    private String content;

    private Long userId;

    private Long postId;

    @CreationTimestamp
    private LocalDateTime commentDate;

    public Recomment() {
    }

    public Recomment(Long id, Long commentId, String content,
                     Long userId, Long postId, LocalDateTime commentDate) {
        this.id = id;
        this.commentId = commentId;
        this.content = content;
        this.userId = userId;
        this.postId = postId;
        this.commentDate = commentDate;
    }

    public Recomment(String content, Long commentId, Long userId, Long postId) {
        this.content = content;
        this.commentId = commentId;
        this.userId = userId;
        this.postId = postId;
    }

    public Long id() {
        return id;
    }

    public Long commentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getPostId() {
        return postId;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public ReCommentDto toDto() {
        return new ReCommentDto(id, content, commentId, postId, userId,
            commentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    public static Recomment fake() {
        return new Recomment(1L, 1L, "대댓글", 1L, 1L, LocalDateTime.now());
    }

    public void patch(String content) {
        this.content = content;
    }
}

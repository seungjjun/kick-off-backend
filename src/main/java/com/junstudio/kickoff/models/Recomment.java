package com.junstudio.kickoff.models;

import com.junstudio.kickoff.dtos.ReCommentDto;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Embedded;
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

    @Embedded
    private Content content;

    @Embedded
    private UserId userId;

    @Embedded
    private PostId postId;

    @CreationTimestamp
    private LocalDateTime commentDate;

    public Recomment() {
    }

    public Recomment(Long id, Long commentId, Content content,
                     UserId userId, PostId postId, LocalDateTime commentDate) {
        this.id = id;
        this.commentId = commentId;
        this.content = content;
        this.userId = userId;
        this.postId = postId;
        this.commentDate = commentDate;
    }

    public Recomment(Content content, Long commentId, UserId userId, PostId postId) {
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

    public Content content() {
        return content;
    }

    public UserId userId() {
        return userId;
    }

    public PostId postId() {
        return postId;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public ReCommentDto toDto() {
        return new ReCommentDto(id, content.value(), commentId, postId.value(), userId.value(),
            commentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    public static Recomment fake() {
        return new Recomment(
            1L,
            1L,
            new Content("대댓글"),
            new UserId(1L),
            new PostId(1L),
            LocalDateTime.now());

    }

    public void patch(String content) {
        this.content = new Content(content);
    }
}

package com.junstudio.kickoff.models;

import com.junstudio.kickoff.dtos.CommentDto;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Embedded;
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

    @Embedded
    private Content content;

    @Embedded
    private UserId userId;

    @Embedded
    private PostId postId;

    private boolean isDeleted;

    @CreationTimestamp
    private LocalDateTime commentDate;

    public Comment() {
    }

    public Comment(Long id, Content content, UserId userId, PostId postId,
                   LocalDateTime commentDate) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.postId = postId;
        this.isDeleted = false;
        this.commentDate = commentDate;
    }

    public Comment(Content content, UserId userId, PostId postId) {
        this.content = content;
        this.userId = userId;
        this.postId = postId;
        this.isDeleted = false;
    }

    public Long id() {
        return id;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public LocalDateTime commentDate() {
        return commentDate;
    }

    public CommentDto toDto() {
        return new CommentDto(id, content.value(), userId.value(), postId.value(), isDeleted,
            commentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    public static Comment fake() {
        return new Comment(1L, new Content("reply"), new UserId(1L), new PostId(1L), LocalDateTime.now());
    }

    public void patch(String content) {
        this.content = new Content(content);
    }

    public void delete() {
        this.isDeleted = true;
    }
}

package com.junstudio.kickoff.models;

import com.junstudio.kickoff.dtos.LikeDto;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LIKES")
public class Like {
    @Id
    @GeneratedValue
    @Column(name = "like_id")
    private Long id;

    @Embedded
    private PostId postId;

    @Embedded
    private UserId userId;

    private Like() {
    }

    public Like(Long id, PostId postId, UserId userId) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
    }

    public Like(Long postId, Long userId) {
        this.postId = new PostId(postId);
        this.userId = new UserId(userId);
    }

    public Long id() {
        return id;
    }

    public PostId postId() {
        return postId;
    }

    public UserId userId() {
        return userId;
    }

    public LikeDto toDto() {
        return new LikeDto(id, postId.value(), userId.value());
    }

    public static Like fake() {
        return new Like(1L, new PostId(1L), new UserId(1L));
    }
}

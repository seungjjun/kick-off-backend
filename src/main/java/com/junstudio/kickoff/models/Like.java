package com.junstudio.kickoff.models;

import com.junstudio.kickoff.dtos.LikeDto;

import javax.persistence.Column;
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

    private Long postId;

    private Long userId;

    private Like() {
    }

    public Like(Long id, Long postId, Long userId) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
    }

    public Like(Long postId, Long userId) {
        this.postId = postId;
        this.userId = userId;
    }

    public Long id() {
        return id;
    }

    public Long postId() {
        return postId;
    }

    public Long userId() {
        return userId;
    }

    public LikeDto toDto() {
        return new LikeDto(id, postId, userId);
    }

    public LikeDto toLikeDto() {
        return new LikeDto(id, postId, userId);
    }

    public static Like fake() {
        return new Like(1L, 1L, 1L);
    }
}

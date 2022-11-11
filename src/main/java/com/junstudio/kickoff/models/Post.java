package com.junstudio.kickoff.models;

import com.junstudio.kickoff.dtos.PostDetailDto;
import com.junstudio.kickoff.dtos.PostDto;
import com.junstudio.kickoff.dtos.PostWrittenDto;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Post {
    @GeneratedValue
    @Id
    @Column(name = "post_id")
    private Long id;

    @Embedded
    private UserId userId;

    @Embedded
    private PostInformation postInformation;

    private Long categoryId;

    private Long hit;

    @Column(name = "imageUrl", length = 2048)
    private String imageUrl;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Post() {
    }

    public Post(Long id, UserId userId, Long categoryId,
                PostInformation postInformation,
                Long hit, String imageUrl,
                LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.postInformation = postInformation;
        this.hit = hit;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
    }

    public Post(PostInformation postInformation, Long hit,
                String imageUrl, Long userId, Long categoryId) {
        this.postInformation = postInformation;
        this.hit = hit;
        this.imageUrl = imageUrl;
        this.userId = new UserId(userId);
        this.categoryId = categoryId;
    }

    public Long id() {
        return id;
    }

    public UserId userId() {
        return userId;
    }

    public Long categoryId() {
        return categoryId;
    }

    public PostInformation postInformation() {
        return postInformation;
    }

    public Long hit() {
        return hit;
    }

    public String imageUrl() {
        return imageUrl;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public PostDto toDto() {
        return new PostDto(id, postInformation, categoryId, userId, hit,
            createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), imageUrl);
    }

    public void updateHit(Long hit) {
        this.hit = hit + 1L;
    }

    public PostWrittenDto postWrittenDto() {
        return new PostWrittenDto(id);
    }

    public PostDetailDto toDetailDto(Category category) {
        return new PostDetailDto(id, postInformation, hit, category,
            createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), imageUrl);
    }

    public static Post fake() {
        return new Post(1L, new UserId(1L), 1L,
            new PostInformation("Son is EPL King",
                "Son is the first Asian to score EPL"),
            3L, "imageUrl", LocalDateTime.now());
    }

    public void patch(String title, String content, Long categoryId, String imageUrl) {
        this.postInformation = new PostInformation(title, content);
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
    }
}

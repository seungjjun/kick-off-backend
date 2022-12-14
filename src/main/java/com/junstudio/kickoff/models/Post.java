package com.junstudio.kickoff.models;

import com.junstudio.kickoff.dtos.PostDetailDto;
import com.junstudio.kickoff.dtos.PostDto;
import com.junstudio.kickoff.dtos.PostWrittenDto;
import com.junstudio.kickoff.dtos.StatisticsPostDto;
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

    @Embedded
    private Hit hit;

    @Embedded
    private BoardId boardId;

    @Embedded
    private Image image;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Post() {
    }

    public Post(Long id, UserId userId, BoardId boardId,
                PostInformation postInformation,
                Hit hit, Image image,
                LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.boardId = boardId;
        this.postInformation = postInformation;
        this.hit = hit;
        this.image = image;
        this.createdAt = createdAt;
    }

    public Post(PostInformation postInformation, Hit hit,
                Image image, UserId userId, BoardId boardId) {
        this.postInformation = postInformation;
        this.hit = hit;
        this.image = image;
        this.userId = userId;
        this.boardId = boardId;
    }

    public Long id() {
        return id;
    }

    public UserId userId() {
        return userId;
    }

    public BoardId getBoardId() {
        return boardId;
    }

    public PostInformation postInformation() {
        return postInformation;
    }

    public Hit hit() {
        return hit;
    }

    public Image image() {
        return image;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public PostDto toDto() {
        return new PostDto(id, postInformation, boardId.value(), userId.value(), hit.number(),
            createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), image.url());
    }

    public void updateHit(Long hit) {
        this.hit = new Hit(hit + 1L);
    }

    public PostWrittenDto postWrittenDto() {
        return new PostWrittenDto(id);
    }

    public PostDetailDto toDetailDto(Board board, User user) {
        return new PostDetailDto(id, postInformation, hit.number(), board, user,
            createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), image.url());
    }

    public static Post fake() {
        return new Post(1L, new UserId(1L), new BoardId(1L),
            new PostInformation("Son is EPL King",
                "Son is the first Asian to score EPL"),
            new Hit(3L), new Image("imageUrl"), LocalDateTime.now());
    }

    public void patch(String title, String content, Long boardId, String imageUrl) {
        this.postInformation = new PostInformation(title, content);
        this.boardId = new BoardId(boardId);
        this.image = new Image(imageUrl);
    }

    public StatisticsPostDto toStatisticsDto() {
        return new StatisticsPostDto(
            id,
            postInformation,
            userId.value(),
            hit.number(),
            createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );
    }
}

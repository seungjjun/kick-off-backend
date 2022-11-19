package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.Board;
import com.junstudio.kickoff.models.Category;
import com.junstudio.kickoff.models.PostInformation;
import com.junstudio.kickoff.models.User;

public class PostDetailDto {
    private final Long id;

    private final PostInformation postInformation;

    private final BoardDto board;

    private final UserDto user;

    private final Long hit;

    private final Long likeNumber;

    private final String createdAt;

    private final String imageUrl;

    public PostDetailDto(Long id, PostInformation postInformation, Long hit, Long likeNumber,
                         Board board, User user, String createdAt, String imageUrl) {
        this.id = id;
        this.postInformation = postInformation;
        this.board = board.toDto();
        this.user = user.toDto();
        this.hit = hit;
        this.likeNumber = likeNumber;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public PostInformation getPostInformation() {
        return postInformation;
    }

    public BoardDto getBoard() {
        return board;
    }

    public UserDto getUser() {
        return user;
    }

    public Long getHit() {
        return hit;
    }

    public Long getLikeNumber() {
        return likeNumber;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

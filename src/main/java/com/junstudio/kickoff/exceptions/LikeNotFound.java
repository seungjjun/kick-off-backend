package com.junstudio.kickoff.exceptions;

public class LikeNotFound extends RuntimeException {
    public LikeNotFound() {
        super("좋아요가 없습니다.");
    }
}

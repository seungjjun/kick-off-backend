package com.junstudio.kickoff.exceptions;

public class RecommentNotFount extends RuntimeException {
    public RecommentNotFount() {
        super("댓글을 찾을 수 없습니다.");
    }
}

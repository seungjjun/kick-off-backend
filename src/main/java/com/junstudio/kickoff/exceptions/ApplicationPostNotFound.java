package com.junstudio.kickoff.exceptions;

public class ApplicationPostNotFound extends RuntimeException{
    public ApplicationPostNotFound() {
        super("신청 게시글을 찾을 수 없습니다.");
    }
}

package com.junstudio.kickoff.exceptions;

public class BoardNotFound extends RuntimeException {
    public BoardNotFound() {
        super("게시판을 찾지 못했습니다.");
    }
}

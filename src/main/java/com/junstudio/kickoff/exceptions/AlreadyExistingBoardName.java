package com.junstudio.kickoff.exceptions;

public class AlreadyExistingBoardName extends RuntimeException {
    public AlreadyExistingBoardName() {
        super("이미 존재하는 게시판입니다.");
    }
}

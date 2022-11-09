package com.junstudio.kickoff.exceptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("유저를 찾을 수 없습니다.");
    }
}

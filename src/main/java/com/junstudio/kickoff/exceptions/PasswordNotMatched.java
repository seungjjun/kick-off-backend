package com.junstudio.kickoff.exceptions;

public class PasswordNotMatched extends RuntimeException {
    public PasswordNotMatched() {
        super("비밀번호가 일치하지 않습니다.");
    }
}

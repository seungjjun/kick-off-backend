package com.junstudio.kickoff.exceptions;

public class AlreadyExistingName extends RuntimeException {
    public AlreadyExistingName() {
        super("이미 사용중입니다. 다른 닉네임을 입력해주세요.");
    }
}

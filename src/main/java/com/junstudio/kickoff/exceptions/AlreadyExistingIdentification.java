package com.junstudio.kickoff.exceptions;

public class AlreadyExistingIdentification extends RuntimeException {
    public AlreadyExistingIdentification() {
        super("해당 아이디는 사용할 수 없습니다");
    }
}

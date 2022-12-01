package com.junstudio.kickoff.exceptions;

public class AlreadyAppliedUser extends RuntimeException {
    public AlreadyAppliedUser() {
        super("이미 신청 상태입니다.");
    }
}

package com.junstudio.kickoff.exceptions;

public class AdminNotFound extends RuntimeException {
    public AdminNotFound() {
        super("관리자 계정이 아닙니다.");
    }
}

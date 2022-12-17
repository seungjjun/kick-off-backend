package com.junstudio.kickoff.exceptions;

public class NotificationNotFound extends RuntimeException {
    public NotificationNotFound() {
        super("알림을 찾지 못했습니다.");
    }
}

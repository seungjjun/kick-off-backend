package com.junstudio.kickoff.dtos;

import java.util.List;

public class NotificationsDto {
    private final List<NotificationDto> notifications;

    public NotificationsDto(List<NotificationDto> notifications) {
        this.notifications = notifications;
    }

    public List<NotificationDto> getNotifications() {
        return notifications;
    }
}

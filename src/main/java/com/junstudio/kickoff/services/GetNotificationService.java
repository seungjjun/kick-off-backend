package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.NotificationDto;
import com.junstudio.kickoff.dtos.NotificationsDto;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.Notification;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.NotificationRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetNotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public GetNotificationService(NotificationRepository notificationRepository,
                                  UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public NotificationsDto notifications(String identification) {
        User user = userRepository.findByIdentification(identification)
            .orElseThrow(UserNotFound::new);

        List<NotificationDto> notifications = notificationRepository.findAllByReceiverId(user.id())
            .stream()
            .map(Notification::toDto)
            .collect(Collectors.toList());

        return new NotificationsDto(notifications);
    }

    public boolean checkNotification(String identification) {
        User user = userRepository.findByIdentification(identification)
            .orElseThrow(UserNotFound::new);

        List<Notification> notifications =
            notificationRepository.findAllByReceiverId(user.id());

        return notifications.stream().anyMatch(notification -> !notification.isRead());
    }
}

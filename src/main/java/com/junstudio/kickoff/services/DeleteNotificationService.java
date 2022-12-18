package com.junstudio.kickoff.services;

import com.junstudio.kickoff.exceptions.NotificationNotFound;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.Notification;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.NotificationRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeleteNotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public DeleteNotificationService(NotificationRepository notificationRepository,
                                     UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public void deleteNotification(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
            .orElseThrow(NotificationNotFound::new);

        notificationRepository.delete(notification);
    }

    public void deleteAllNotification(String identification) {
        User user = userRepository.findByIdentification(identification)
            .orElseThrow(UserNotFound::new);

        notificationRepository.deleteAllByReceiverId(user.id());
    }

    public void deleteReadNotification(String identification) {
        User user = userRepository.findByIdentification(identification)
            .orElseThrow(UserNotFound::new);

        List<Notification> notifications = notificationRepository.findAllByReceiverId(user.id());

        notifications.forEach(notification -> {
            if(notification.isRead()) {
                notificationRepository.delete(notification);
            }
        });
    }
}

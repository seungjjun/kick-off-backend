package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Notification;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.NotificationRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteNotificationServiceTest {
    NotificationRepository notificationRepository;
    UserRepository userRepository;

    DeleteNotificationService deleteNotificationService;

    Notification notification;

    User user;

    @BeforeEach
    void setup() {
        notification = Notification.fake();
        user = User.fake();

        notificationRepository = mock(NotificationRepository.class);
        userRepository = mock(UserRepository.class);

        deleteNotificationService =
            new DeleteNotificationService(notificationRepository, userRepository);

        given(userRepository.findByIdentification(any()))
            .willReturn(Optional.of(user));
    }

    @Test
    void deleteNotification() {
        given(notificationRepository.findById(any())).willReturn(Optional.of(notification));

        deleteNotificationService.deleteNotification(notification.id());

        verify(notificationRepository).delete(notification);
    }

    @Test
    void deleteAllNotification() {
        deleteNotificationService.deleteAllNotification(user.identification());

        verify(notificationRepository).deleteAllByReceiverId(user.id());
    }

    @Test
    void deleteReadNotification() {
        given(notificationRepository.findAllByReceiverId(any(Long.class)))
            .willReturn(List.of(notification));

        notification.read();

        deleteNotificationService.deleteReadNotification(user.identification());

        verify(notificationRepository).delete(notification);
    }
}

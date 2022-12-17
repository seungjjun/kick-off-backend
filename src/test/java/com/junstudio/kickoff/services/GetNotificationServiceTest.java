package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.NotificationDto;
import com.junstudio.kickoff.dtos.NotificationsDto;
import com.junstudio.kickoff.models.Notification;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.NotificationRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetNotificationServiceTest {

    @MockBean
    private NotificationRepository notificationRepository;

    GetNotificationService getNotificationService;

    UserRepository userRepository;

    User user;
    Notification notification;

    @BeforeEach
    void setup() {
        notificationRepository = mock(NotificationRepository.class);
        userRepository = mock(UserRepository.class);

        getNotificationService = new GetNotificationService(notificationRepository, userRepository);

        user = User.fake();
        notification = Notification.fake();

        given(userRepository.findByIdentification(any())).willReturn(Optional.of(user));

        given(notificationRepository.findAllByReceiverId(any(Long.class)))
            .willReturn(List.of(notification));
    }

    @Test
    void notifications() {
        NotificationsDto notifications = getNotificationService.notifications(user.identification());

        assertThat(notifications.getNotifications().size()).isEqualTo(1);
    }

    @Test
    void checkNotification() {
        notification.read();

        boolean checkNotifications = getNotificationService.checkNotification(user.identification());

        assertThat(checkNotifications).isFalse();
    }
}

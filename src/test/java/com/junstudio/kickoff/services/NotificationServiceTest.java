package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Notification;
import com.junstudio.kickoff.repositories.NotificationRepository;
import com.junstudio.kickoff.repositories.SseEmitterRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class NotificationServiceTest {
    @MockBean
    private SseEmitterRepository sseEmitterRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private NotificationRepository notificationRepository;

    @SpyBean
    private NotificationService notificationService;

    Notification notification;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        notificationRepository = mock(NotificationRepository.class);
        sseEmitterRepository = mock(SseEmitterRepository.class);

        notificationService =
            new NotificationService(sseEmitterRepository, notificationRepository, userRepository);

        notification = spy(Notification.fake());
    }

    @Test
    void createNotification() {
        Notification notification =
            notificationService.createNotification(1L, 1L, "son", "test");

        assertThat(notification.sender()).isEqualTo("son");
        assertThat(notification.content()).isEqualTo("test");
    }

    @Test
    void read() {
        given(notificationRepository.findById(any())).willReturn(Optional.of(notification));

        notificationService.read(notification.id());

        verify(notification).read();
    }
}

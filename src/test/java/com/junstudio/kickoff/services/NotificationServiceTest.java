package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Grade;
import com.junstudio.kickoff.models.Notification;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.NotificationRepository;
import com.junstudio.kickoff.repositories.SseEmitterRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
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

    User user;
    User receiver;

    Post post;
    Notification notification;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        notificationRepository = mock(NotificationRepository.class);
        sseEmitterRepository = mock(SseEmitterRepository.class);

        notificationService =
            new NotificationService(sseEmitterRepository, notificationRepository, userRepository);

        user = User.fake();
        receiver = new User(2L, "pikachu", "password", "jin", "image", new Grade("프로"), false, LocalDateTime.now());

        post = Post.fake();
        notification = Notification.fake();
    }

    @Test
    void sendNotification() {
        given(userRepository.findById(any())).willReturn(Optional.of(user));

        given(userRepository.findById(any())).willReturn(Optional.of(receiver));

        Notification notification1 =
            notificationService.createNotification(receiver.id(), post.id(), user.name(), notification.content());

        notificationService.sendNotification(
            receiver.id(),
            user.id(),
            post.id(),
            notification.content(),
            user.identification()
        );

        verify(notificationRepository).save(notification1);
    }
}

package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class NotificationTest {
    @Test
    void creation() {
        Notification notification = new Notification(1L, 1L, "황희찬", "content", false, LocalDateTime.now());

        assertThat(notification.sender()).isEqualTo("황희찬");
    }

    @Test
    void fake() {
        Notification notification = Notification.fake();

        assertThat(notification.sender()).isEqualTo("pikachu");
        assertThat(notification.content()).isEqualTo("Million volt");
    }
}

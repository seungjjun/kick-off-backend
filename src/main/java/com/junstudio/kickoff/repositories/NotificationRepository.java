package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Notification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByReceiverId(Long receiverId);

    List<Notification> findAllByReceiverId(Long receiverId, Sort notificationId);

    void deleteAllByReceiverId(Long receiverId);
}

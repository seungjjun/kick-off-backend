package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}

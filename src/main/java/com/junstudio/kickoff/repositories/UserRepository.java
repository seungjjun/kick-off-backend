package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdentification(String identification);

    boolean existsByName(String name);

    boolean existsByIdentification(String identification);

    Optional<User> findByName(String name);

    List<User> findByCreatedAtBetween(LocalDateTime startDatetime, LocalDateTime endDatetime);
}

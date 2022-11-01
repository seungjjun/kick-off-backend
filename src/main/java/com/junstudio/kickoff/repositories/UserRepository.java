package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

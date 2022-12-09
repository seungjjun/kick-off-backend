package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByIdentification(String identification);
}

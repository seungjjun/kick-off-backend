package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.ApplicationPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationPostRepository extends JpaRepository<ApplicationPost, Long> {
    boolean existsByApplicant_Name(String name);

    List<ApplicationPost> findAllByApplicant_Name(String name);
}

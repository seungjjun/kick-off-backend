package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

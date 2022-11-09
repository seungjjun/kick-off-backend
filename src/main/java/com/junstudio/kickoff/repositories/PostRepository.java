package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByCategoryId(Long categoryId);
}

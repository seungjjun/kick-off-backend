package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByCategoryId(Long categoryId, Pageable pageable);

    Page<Post> findAll(Pageable pageable);
}

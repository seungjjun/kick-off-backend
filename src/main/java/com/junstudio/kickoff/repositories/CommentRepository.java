package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByPostId(Long postId, Pageable pageable);

    void deleteAllById(Long postId);

    Comment getReferenceByPostId(Long postId);
}

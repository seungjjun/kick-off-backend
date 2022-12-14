package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByPostId_Value(Long postId, Pageable pageable);

    void deleteAllById(Long postId);

    Comment getReferenceByPostId_Value(Long postId);

    boolean existsByPostId_Value(Long postId);

    List<Comment> findAllByPostId_Value(Long postId);

    List<Comment> findAllByUserId_Value(Long userId);

    boolean existsByUserId_Value(Long userId);

    List<Comment> findByCommentDateBetween(LocalDateTime startDatetime, LocalDateTime endDatetime);
}

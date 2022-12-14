package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Recomment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RecommentRepository extends JpaRepository<Recomment, Long> {
    List<Recomment> findAllByPostId_Value(Long postId);

    Recomment getReferenceByPostId_Value(Long postId);

    List<Recomment> findAllByUserId_Value(Long userId);

    List<Recomment> findAllByCommentId(Long id);

    void deleteAllById(Long postId);

    boolean existsByCommentId(Long commentId);

    boolean existsByPostId_Value(Long postId);

    boolean existsByUserId_Value(Long userId);

    List<Recomment> findByCommentDateBetween(LocalDateTime startDatetime, LocalDateTime endDatetime);
}

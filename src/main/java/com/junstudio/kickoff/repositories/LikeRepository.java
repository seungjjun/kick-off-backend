package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPostId_Value(Long postId);

    List<Like> findAllByPostId_Value(Long postId);

    void deleteAllById(Long postId);

    Like getReferenceByPostId_Value(Long postId);

    boolean existsByPostId_Value(Long postId);

    List<Like> findAllByUserId_Value(Long userId);

    void deleteByPostId_Value(Long postId);

    boolean existsByUserId_Value(Long userId);
}

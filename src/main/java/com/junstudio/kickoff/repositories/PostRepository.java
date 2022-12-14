package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);

    Page<Post> findAllByBoardId_Value(Long boardId, Pageable pageable);

    List<Post> findAllByBoardId_Value(Long boardId);

    List<Post> findAllByUserId_Value(Long userId);

    Page<Post> findByPostInformation_TitleContaining(String keyword, Pageable pageable);

    Page<Post> findByBoardIdAndPostInformation_TitleContaining(Long boardId, String keyword, Pageable pageable);

    Page<Post> findByPostInformation_ContentContaining(String keyword, Pageable pageable);

    Page<Post> findByBoardIdAndPostInformation_ContentContaining(Long boardId, String keyword, Pageable pageable);

    Page<Post> findByUserId(UserId userId, Pageable pageable);

    boolean existsByUserId_Value(Long userId);

    List<Post> findTop3ByOrderByHit_NumberDesc();

    List<Post> findByCreatedAtBetween(LocalDateTime createdAt, LocalDateTime endDatetime);

}

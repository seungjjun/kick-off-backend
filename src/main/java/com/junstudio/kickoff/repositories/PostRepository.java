package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);

    Page<Post> findAllByBoardId(Long boardId, Pageable pageable);

    List<Post> findAllByUserId(UserId userId);
}

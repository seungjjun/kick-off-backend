package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.dtos.PostDto;
import com.junstudio.kickoff.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
//    Page<Post> findAllByCategoryId(Long categoryId, Pageable pageable);

    Page<Post> findAll(Pageable pageable);

    Page<Post> findAllByBoardId(Long boardId, Pageable pageable);
}

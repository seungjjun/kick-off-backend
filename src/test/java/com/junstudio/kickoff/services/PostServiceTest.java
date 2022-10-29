package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PostServiceTest {
  PostRepository postRepository;
  PostService postService;

  @BeforeEach
  void setup() {
    postRepository = mock(PostRepository.class);
    postService = new PostService(postRepository);
  }

  @Test
  void posts() {
    Post post = mock(Post.class);

    given(postRepository.findAll()).willReturn(List.of(post));

    List<Post> posts = postService.posts();

    assertThat(posts).hasSize(1);
  }

  @Test
  void findPost() {
    Post post = new Post(1L, "EPL start", "Today EPL start", "SkySport", "EPL", 1L);

    given(postRepository.findById(any())).willReturn(Optional.of(post));

    Post foundedPost = postService.findPost(post.getId());

    assertThat(foundedPost.getTitle()).isEqualTo("EPL start");
    assertThat(foundedPost.getAuthor()).isEqualTo("SkySport");

    verify(postRepository).findById(any());
  }

  @Test
  void write() {
    Post post = new Post("conte", "reshuffle", "EPL", 1L);

    postService.write(post.getTitle(), post.getContent(), post.getCategory());

    given(postRepository.save(post)).willReturn(post);

    verify(postRepository).save(any(Post.class));
  }
}

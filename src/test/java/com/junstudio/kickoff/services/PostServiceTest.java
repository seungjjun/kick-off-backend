package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

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
}

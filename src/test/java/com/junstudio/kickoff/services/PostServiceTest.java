package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Category;
import com.junstudio.kickoff.models.Grade;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.CategoryRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PostServiceTest {
  PostRepository postRepository;
  UserRepository userRepository;
  CategoryRepository categoryRepository;
  PostService postService;

  @BeforeEach
  void setup() {
    postRepository = mock(PostRepository.class);
    userRepository = mock(UserRepository.class);
    categoryRepository = mock(CategoryRepository.class);
    postService = new PostService(postRepository, userRepository, categoryRepository);
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
    Post post = new Post(1L, 1L, 1L, "EPL start",
        "손흥민 아시아인 최초 EPL 득점왕", 3L, "imageUrl", LocalDateTime.now());

    given(postRepository.findById(any())).willReturn(Optional.of(post));

    Post foundedPost = postService.findPost(post.id());

    assertThat(foundedPost.title()).isEqualTo("EPL start");

    verify(postRepository).findById(any());
  }

  @Test
  void write() {
    User user = mock(User.class);
    given(userRepository.findById(user.id())).willReturn(Optional.of(user));

    Category category = mock(Category.class);
    given(categoryRepository.findById(category.id())).willReturn(Optional.of(category));

    Post post = new Post("conte", "reshuffle", 1L, "imageUrl", user.id(), category.id());

    postService.write(post.title(), post.content(),
        post.imageUrl(), user.id(), category.id());

    verify(postRepository).save(any(Post.class));
  }
}

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
    Post post = new Post(1L, new User(), new Category(), List.of(), List.of(),
        "EPL start", "손흥민 아시아인 최초 EPL 득점왕", 3L, "imageUrl", LocalDateTime.now());

    given(postRepository.findById(any())).willReturn(Optional.of(post));

    Post foundedPost = postService.findPost(post.getId());

    assertThat(foundedPost.getTitle()).isEqualTo("EPL start");

    verify(postRepository).findById(any());
  }

  @Test
  void write() {
    User user = mock(User.class);
    given(userRepository.findById(user.getId())).willReturn(Optional.of(user));

    Category category = mock(Category.class);
    given(categoryRepository.findById(category.getId())).willReturn(Optional.of(category));

    Post post = new Post("conte", "reshuffle", 1L, "imageUrl", user, category);

    postService.write(post.getTitle(), post.getContent(),
        post.getImageUrl(), post.getUser().getId(), post.getCategory().getId());

    given(postRepository.save(post)).willReturn(post);

    verify(postRepository).save(any(Post.class));
  }
}

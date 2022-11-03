package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.LikeRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class LikeServiceTest {
  @MockBean
  private LikeRepository likeRepository;

  @MockBean
  private PostRepository postRepository;

  @MockBean
  private UserRepository userRepository;

  @SpyBean
  private LikeService likeService;

  private User user;

  private Post post;

  private Like like;

  @BeforeEach
  void setup() {
    likeRepository = mock(LikeRepository.class);
    postRepository = mock(PostRepository.class);
    userRepository = mock(UserRepository.class);

    likeService = new LikeService(
        likeRepository, postRepository, userRepository);

    user = mock(User.class);
    given(userRepository.findById(any(Long.class))).willReturn(Optional.of(user));

    post = mock(Post.class);
    given(postRepository.findById(any(Long.class))).willReturn(Optional.of(post));
  }

  @Test
  void countLike() {
    like = new Like(1L, post.id(), user.id());

    likeService.countLike(user.id(), post.id());
    verify(likeRepository).save(any());
  }

  @Test
  void deleteLike() {
    like = new Like(1L, post.id(), user.id());

    given(likeRepository.findByPostId(any(Long.class)))
        .willReturn(Optional.of(like));

    likeService.countLike(post.id(), user.id());
    verify(likeRepository).deleteById(any(Long.class));
  }
}

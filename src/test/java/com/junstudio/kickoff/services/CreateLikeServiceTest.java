package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.LikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateLikeServiceTest {
    @MockBean
    private LikeRepository likeRepository;

    @MockBean
    private CreateLikeService createLikeService;

    User user;
    Post post;
    Like like;

    @BeforeEach
    void setup() {
        like = Like.fake();

        likeRepository = mock(LikeRepository.class);

        createLikeService = new CreateLikeService(likeRepository);

        user = User.fake();
        post = Post.fake();
    }

    @Test
    void countLike() {
        createLikeService.countLike(user.id(), post.id());
        verify(likeRepository).save(any());
    }

    @Test
    void deleteLike() {
        given(likeRepository.existsByPostId_Value(post.id())).willReturn(true);

        given(likeRepository.findAllByUserId_Value(user.id()))
            .willReturn(List.of(like));

        createLikeService.countLike(post.id(), user.id());

        verify(likeRepository).deleteById(any(Long.class));
    }
}

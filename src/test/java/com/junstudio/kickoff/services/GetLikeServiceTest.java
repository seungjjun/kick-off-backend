package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.LikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetLikeServiceTest {
    @MockBean
    private LikeRepository likeRepository;

    @SpyBean
    private GetLikeService getLikeService;

    private User user;

    Like like;

    @BeforeEach
    void setup() {
        like = new Like(1L, 1L, 1L);

        likeRepository = mock(LikeRepository.class);

        getLikeService = new GetLikeService(likeRepository);
    }

    @Test
    void like() {
        given(likeRepository.findAll()).willReturn(List.of(like));

        List<Like> likes = getLikeService.likes();

        assertThat(likes).hasSize(1);
    }

    @Test
    void findLike() {
        given(likeRepository.findAllByPostId(any(Long.class))).willReturn(List.of(like));

        List<Like> foundLike = getLikeService.findLike(any(Long.class));

        assertThat(foundLike).hasSize(1);
    }
}

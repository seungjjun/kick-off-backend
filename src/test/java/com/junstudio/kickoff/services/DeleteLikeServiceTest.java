package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.repositories.LikeRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteLikeServiceTest {
    @Test
    void deleteLike() {
        Like like = Like.fake();

        LikeRepository likeRepository = mock(LikeRepository.class);

        DeleteLikeService deleteLikeService = new DeleteLikeService(likeRepository);

        deleteLikeService.deleteLike(List.of(like.postId()));

        verify(likeRepository).deleteByPostId(like.postId());
    }
}

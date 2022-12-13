package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.dtos.PostsByDateDto;
import com.junstudio.kickoff.dtos.StatisticsPostsDto;
import com.junstudio.kickoff.dtos.TodayCreatePostsDto;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.LikeRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostAdminServiceTest {
    PostRepository postRepository;
    UserRepository userRepository;

    Post post;
    User user;

    GetPostAdminService getPostAdminService;

    @BeforeEach
    void setup() {
        post = Post.fake();
        user = User.fake();

        postRepository = mock(PostRepository.class);
        userRepository = mock(UserRepository.class);

        getPostAdminService = new GetPostAdminService(postRepository, userRepository);
    }

    @Test
    void mostViewedPosts() {
        given(postRepository.findTop3ByOrderByHitDesc()).willReturn(List.of(post));
        given(userRepository.findById(any())).willReturn(Optional.of(user));

        StatisticsPostsDto mostViewedPosts = getPostAdminService.mostViewedPosts();

        assertThat(mostViewedPosts.getPosts().size()).isEqualTo(1);
        assertThat(mostViewedPosts.getUsers().size()).isEqualTo(1);
    }

    @Test
    void todayCreatedPosts() {
        given(postRepository.findByCreatedAtBetween(any(), any())).willReturn(List.of(post));

        TodayCreatePostsDto todayCreatePostsDto = getPostAdminService.todayCreatedPosts();

        assertThat(todayCreatePostsDto.getPosts().size()).isEqualTo(1);

        assertThat(todayCreatePostsDto.getPosts().get(0).postInformation().getTitle())
            .isEqualTo("Son is EPL King");
    }

    @Test
    void weekPosts() {
        given(postRepository.findByCreatedAtBetween(any(), any())).willReturn(List.of(post));

        PostsByDateDto postsNumber = getPostAdminService.weekPosts();

        assertThat(postsNumber.getTodayPostsNumber()).isEqualTo(1);
    }

    @Test
    void posts() {
        given(postRepository.findAll()).willReturn(List.of(post));

        int totalPostNumber = getPostAdminService.posts();

        assertThat(totalPostNumber).isEqualTo(1);
    }
}

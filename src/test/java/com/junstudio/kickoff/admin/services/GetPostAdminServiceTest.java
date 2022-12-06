package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.dtos.StatisticsPostsDto;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.UserRepository;
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

    @Test
    void mostViewedPosts() {
        Post post = Post.fake();
        User user = User.fake();

        postRepository = mock(PostRepository.class);
        userRepository = mock(UserRepository.class);

        GetPostAdminService getPostAdminService =
            new GetPostAdminService(postRepository, userRepository);

        given(postRepository.findTop3ByOrderByHitDesc()).willReturn(List.of(post));
        given(userRepository.findById(any())).willReturn(Optional.of(user));

        StatisticsPostsDto mostViewedPosts = getPostAdminService.mostViewedPosts();

        assertThat(mostViewedPosts.getPosts().size()).isEqualTo(1);
        assertThat(mostViewedPosts.getUsers().size()).isEqualTo(1);
    }
}

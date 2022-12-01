package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.dtos.ApplicationPostsDto;
import com.junstudio.kickoff.models.ApplicationPost;
import com.junstudio.kickoff.repositories.ApplicationPostRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetApplicationPostServiceTest {
    @Test
    void applicationPosts() {
        ApplicationPost applicationPost = ApplicationPost.fake();

        ApplicationPostRepository applicationPostRepository =
            mock(ApplicationPostRepository.class);

        GetApplicationPostService getApplicationPostService =
            new GetApplicationPostService(applicationPostRepository);

        given(applicationPostRepository.findAll())
            .willReturn(List.of(new ApplicationPost(
                applicationPost.id(),
                applicationPost.reason(),
                applicationPost.applicant(),
                applicationPost.creationNumber()
            )));

        ApplicationPostsDto applicationPosts =
            getApplicationPostService.applicationPosts();

        assertThat(applicationPosts.getApplicationPosts().size()).isEqualTo(1);
    }
}

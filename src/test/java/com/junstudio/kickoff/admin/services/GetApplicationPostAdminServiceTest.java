package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.dtos.ApplicationPostsDto;
import com.junstudio.kickoff.models.ApplicationPost;
import com.junstudio.kickoff.repositories.ApplicationPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetApplicationPostAdminServiceTest {
    ApplicationPost applicationPost;

    ApplicationPostRepository applicationPostRepository;

    GetApplicationPostAdminService getApplicationPostAdminService;

    @BeforeEach
    void setup() {
        applicationPost = ApplicationPost.fake();

        applicationPostRepository =
            mock(ApplicationPostRepository.class);

         getApplicationPostAdminService =
             new GetApplicationPostAdminService(applicationPostRepository);
    }

    @Test
    void applicationPosts() {
        given(applicationPostRepository.findAll())
            .willReturn(List.of(new ApplicationPost(
                applicationPost.id(),
                applicationPost.reason(),
                applicationPost.applicant(),
                applicationPost.creationNumber(),
                applicationPost.state()
            )));

        ApplicationPostsDto applicationPosts =
            getApplicationPostAdminService.applicationPosts();

        assertThat(applicationPosts.getApplicationPosts().size()).isEqualTo(1);
    }

    @Test
    void processingPosts() {
        given(applicationPostRepository.findAll())
            .willReturn(List.of(applicationPost));

        int processingPosts = getApplicationPostAdminService.processingPosts();

        assertThat(processingPosts).isEqualTo(1);
    }
}

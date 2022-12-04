package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.models.ApplicationPost;
import com.junstudio.kickoff.repositories.ApplicationPostRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class DeleteApplicationPostAdminServiceTest {
    @Test
    void delete() {
        ApplicationPost applicationPost = spy(ApplicationPost.fake());

        ApplicationPostRepository applicationPostRepository
            = mock(ApplicationPostRepository.class);

        DeleteApplicationPostAdminService deleteApplicationPostAdminService =
            new DeleteApplicationPostAdminService(applicationPostRepository);

        given(applicationPostRepository.findById(any(Long.class)))
            .willReturn(Optional.of(applicationPost));

        deleteApplicationPostAdminService.delete(applicationPost.id());

        verify(applicationPost).changeState(any(String.class));
    }
}

package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.models.ApplicationPost;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.ApplicationPostRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class PatchGradeServiceTest {
    @Test
    void patchGrade() {
        User user = User.fake();
        ApplicationPost applicationPost = spy(ApplicationPost.fake());

        String applicationGrade = "프로";

        UserRepository userRepository = mock(UserRepository.class);

        ApplicationPostRepository applicationPostRepository = mock(ApplicationPostRepository.class);

        PatchGradeService patchGradeService =
            new PatchGradeService(userRepository, applicationPostRepository);

        given(userRepository.findByName(user.name()))
            .willReturn(Optional.of(user));

        given(applicationPostRepository.findById(applicationPost.id()))
            .willReturn(Optional.of(applicationPost));

        patchGradeService.patch(user.id(), applicationGrade, user.name());

        verify(applicationPost).changeState(any(String.class));
    }
}

package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.ApplicationPostRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PatchGradeServiceTest {
    @Test
    void patchGrade() {
        User user = User.fake();
        String applicationGrade = "프로";

        UserRepository userRepository = mock(UserRepository.class);

        ApplicationPostRepository applicationPostRepository = mock(ApplicationPostRepository.class);

        PatchGradeService patchGradeService =
            new PatchGradeService(userRepository, applicationPostRepository);

        given(userRepository.findByName(user.name())).willReturn(Optional.of(user));

        patchGradeService.patch(user.id(), applicationGrade, user.name());

        verify(applicationPostRepository).deleteById(user.id());
    }
}

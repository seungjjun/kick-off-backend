package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class PatchUserAdminServiceTest {
    @MockBean
    private UserRepository userRepository;

    @Test
    void patchUserGrade() {
        User user = spy(User.fake());

        userRepository = mock(UserRepository.class);

        PatchUserAdminService patchUserAdminService = new PatchUserAdminService(userRepository);

        given(userRepository.findById(any(Long.class))).willReturn(Optional.of(user));

        patchUserAdminService.patch(List.of(user.id()), user.grade().name());

        verify(user).changeGrade(any(String.class));
    }
}

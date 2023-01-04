package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.UserDto;
import com.junstudio.kickoff.models.Grade;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class PatchUserServiceTest {
    UserRepository userRepository;

    @Test
    void patch() {
        userRepository = mock(UserRepository.class);
        PatchUserService patchUserService = new PatchUserService(userRepository);

        User user = spy(User.fake());

        given(userRepository.findById(any(Long.class))).willReturn(Optional.of(user));

        UserDto userDto = new UserDto(
            user.id(),
            user.identification(),
            user.name(),
            user.profileImage(),
            user.bucket(),
            user.grade().name(),
            user.isMyToken(),
            user.createdAt().toString()
        );

        patchUserService.patch(user.id(), userDto);

        verify(user).update(user.name(), user.identification());
    }
}

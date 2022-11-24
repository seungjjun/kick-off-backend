package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.UserDto;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

        User user = spy(new User(1L, "jel1y", "encodedPassword",
            "Jun", "profileImage", 1L, false));

        given(userRepository.findById(any(Long.class))).willReturn(Optional.of(user));

        UserDto userDto = new UserDto(
            user.id(),
            user.identification(),
            user.name(),
            user.profileImage(),
            user.isMyToken()
        );

        patchUserService.patch(user.id(), userDto);

        verify(user).update(user.name(), user.identification());
    }
}

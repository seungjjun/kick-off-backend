package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetUserServiceTest {
    @Test
    void findUser() {
        UserRepository userRepository = mock(UserRepository.class);
        GetUserService getUserService = new GetUserService(userRepository);

        User user = new User(1L, "jel1y", "encodedPassword",
            "Jun", "profileImage", 1L);

        given(userRepository.findByIdentification(any()))
            .willReturn(Optional.of(user));

        User foundUser = getUserService.findUser("jel1y");

        assertThat(foundUser.name()).isEqualTo("Jun");
    }
}

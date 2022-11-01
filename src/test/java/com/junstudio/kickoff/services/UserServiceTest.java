package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Grade;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UserServiceTest {
  @Test
  void findUser() {
    UserRepository userRepository = mock(UserRepository.class);
    UserService userService = new UserService(userRepository);

    User user = new User(1L, "jel1y", "encodedPassword",
        "Jun", "profileImage", new Grade(), List.of(), List.of());

    given(userRepository.findById(any(Long.class))).willReturn(Optional.of(user));

    User foundUser = userService.findUser(1L);

    assertThat(foundUser.getName()).isEqualTo("Jun");
  }
}

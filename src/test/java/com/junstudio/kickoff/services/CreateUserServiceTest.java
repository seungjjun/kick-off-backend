package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Grade;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateUserServiceTest {
    @Test
    void register() {
        UserRepository userRepository = mock(UserRepository.class);

        PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

        CreateUserService createUserService
            = new CreateUserService(userRepository, passwordEncoder);

        User user = User.fake();

        createUserService.register(
            user.name(),
            user.identification(),
            user.encodedPassword(),
            user.encodedPassword()
        );

        verify(userRepository).save(any(User.class));
    }
}

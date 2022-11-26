package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateUserServiceTest {
    @Test
    void register() {
        UserRepository userRepository = mock(UserRepository.class);

        PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

        CreateUserService createUserService
            = new CreateUserService(userRepository, passwordEncoder);

        User user = new User(1L, "jel1y", "jun123", "jun", "profileImage", 1L, false);

        createUserService.register(
            user.name(),
            user.identification(),
            user.encodedPassword(),
            user.encodedPassword()
        );

        verify(userRepository).save(any(User.class));
    }
}

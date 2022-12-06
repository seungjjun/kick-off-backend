package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    @Test
    void User() {
        User user = new User(1L, "jel1y", "encodedPassword",
            "Jun", "profileImage", new Grade("아마추어"), false, LocalDateTime.now());

        assertThat(user.identification()).isEqualTo("jel1y");
        assertThat(user.name()).isEqualTo("Jun");
    }

    @Test
    void authenticate() {
        User user = new User(1L, "jel1y", "password",
            "Jun", "profileImage", new Grade("아마추어"), false, LocalDateTime.now());

        PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

        user.changePassword("password", passwordEncoder);

        assertThat(user.authenticate("password", passwordEncoder)).isTrue();
        assertThat(user.authenticate("xxx", passwordEncoder)).isFalse();
    }

    @Test
    void fake() {
        User user = User.fake();

        assertThat(user.grade().name()).isEqualTo("아마추어");
        assertThat(user.name()).isEqualTo("Jun");
    }
}

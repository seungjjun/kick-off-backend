package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    @Test
    void User() {
        User user = new User(1L, "jel1y", "encodedPassword",
            "Jun", "profileImage", 1L);

        assertThat(user.identification()).isEqualTo("jel1y");
        assertThat(user.name()).isEqualTo("Jun");
    }

    @Test
    void authenticate() {
        User user = new User(1L, "jel1y", "password",
            "Jun", "profileImage", 1L);

        PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

        user.changePassword("password", passwordEncoder);

        assertThat(user.authenticate("password", passwordEncoder)).isTrue();
        assertThat(user.authenticate("xxx", passwordEncoder)).isFalse();
    }
}

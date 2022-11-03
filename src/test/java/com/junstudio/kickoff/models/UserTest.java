package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
  @Test
  void User() {
    User user = new User(1L, "jel1y", "encodedPassword",
        "Jun", "profileImage", 1L);

    assertThat(user.identification()).isEqualTo("jel1y");
    assertThat(user.name()).isEqualTo("Jun");
  }
}

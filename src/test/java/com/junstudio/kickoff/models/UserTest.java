package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
  @Test
  void User() {
    User user = new User(1L, "jel1y", "encodedPassword",
        "Jun", "profileImage", new Grade(), List.of(), List.of());

    assertThat(user.getIdentification()).isEqualTo("jel1y");
    assertThat(user.getName()).isEqualTo("Jun");
  }
}

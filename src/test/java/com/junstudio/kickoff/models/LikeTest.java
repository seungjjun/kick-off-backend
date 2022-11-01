package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LikeTest {
  @Test
  void like() {
    User user = new User(1L, "jel1y", "encodedPassword",
        "Jun","profileImage", new Grade(), List.of(), List.of());

    Category category = new Category(1L, "EPL", List.of());

    Post post = new Post(1L, user, category, List.of(), List.of(),
        "손흥민 득점왕 수상", "손흥민 아시아인 최초 EPL 득점왕", 3L, "imageUrl", LocalDateTime.now());
    Like like = new Like(1L, post, user);

    assertThat(like.getPost().getTitle()).isEqualTo("손흥민 득점왕 수상");
  }
}

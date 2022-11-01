package com.junstudio.kickoff.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {
  Post post;

  @BeforeEach
  void setup() {
    User user = new User(1L, "jel1y", "encodedPassword",
        "Jun", "profileImage", new Grade(), List.of(), List.of());

    Category category = new Category(1L, "EPL", List.of());

    post = new Post(1L, user, category, List.of(), List.of(),
        "손흥민 득점왕 수상", "손흥민 아시아인 최초 EPL 득점왕", 3L, "imageUrl", LocalDateTime.now());
  }

  @Test
  void creation() {
    assertThat(post.getTitle()).isEqualTo("손흥민 득점왕 수상");
  }

  @Test
  void updateHit() {
    post.updateHit(post.getHit());

    assertThat(post.getHit()).isEqualTo(4L);
  }
}

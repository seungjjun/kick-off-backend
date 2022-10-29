package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {
  @Test
  void creation() {
    Post post = new Post(1L, "손흥민 득점왕 수상", "굉민재", "EPL", 3L, 20L);

    assertThat(post.getTitle()).isEqualTo("손흥민 득점왕 수상");
    assertThat(post.getAuthor()).isEqualTo("굉민재");
  }

  @Test
  void updateHit() {
    Post post = new Post(
        1L, "손흥민 득점왕 수상", "손흥민 아시아인 최초 EPL 득점왕",
        "로마노 기자", "EPL", 3L);

    post.updateHit(post.getHit());

    assertThat(post.getHit()).isEqualTo(4L);
  }
}

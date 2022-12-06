package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LikeTest {
    @Test
    void like() {
        User user = User.fake();

        Post post = Post.fake();

        Like like = new Like(1L, post.id(), user.id());

        assertThat(like.postId()).isEqualTo(1L);
    }
}

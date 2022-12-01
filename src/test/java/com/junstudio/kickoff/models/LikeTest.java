package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LikeTest {
    @Test
    void like() {
        User user = new User(1L, "jel1y", "encodedPassword",
            "Jun", "profileImage", new Grade("아마추어"), false);

        Post post = Post.fake();

        Like like = new Like(1L, post.id(), user.id());

        assertThat(like.postId()).isEqualTo(1L);
    }
}

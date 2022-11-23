package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.UsersDto;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.LikeRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import com.junstudio.kickoff.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetUserServiceTest {
    private UserRepository userRepository;
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private RecommentRepository recommentRepository;
    private LikeRepository likeRepository;

    User user;
    Post post;
    Comment comment;
    Like like;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        postRepository = mock(PostRepository.class);
        commentRepository = mock(CommentRepository.class);
        recommentRepository = mock(RecommentRepository.class);
        likeRepository = mock(LikeRepository.class);

        post = Post.fake();
        comment = Comment.fake();
        like = Like.fake();

        user = new User(1L, "jel1y", "encodedPassword",
            "Jun", "profileImage", 1L, false);

        given(userRepository.findByIdentification(any()))
            .willReturn(Optional.of(user));
    }

    @Test
    void findMyInformation() {
        GetUserService getUserService =
            new GetUserService(
                userRepository,
                postRepository,
                commentRepository,
                likeRepository,
                recommentRepository
            );

        User foundUser = getUserService.findMyInformation("jel1y");

        assertThat(foundUser.name()).isEqualTo("Jun");
    }

    @Test
    void findUser() {
        GetUserService getUserService =
            new GetUserService(
                userRepository,
                postRepository,
                commentRepository,
                likeRepository,
                recommentRepository
            );

        given(userRepository.findById(user.id())).willReturn(Optional.of(user));
        UsersDto users = getUserService.findUser(user.id(), user.identification());

        assertThat(users.getUser().getName()).isEqualTo("Jun");
        assertThat(users.getUser().getIdentification()).isEqualTo("jel1y");
    }
}

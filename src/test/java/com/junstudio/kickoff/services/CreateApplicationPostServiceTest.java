package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.ApplicationPost;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.ApplicationPostRepository;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateApplicationPostServiceTest {
    @MockBean
    ApplicationPostRepository applicationPostRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    PostRepository postRepository;

    @MockBean
    CommentRepository commentRepository;

    @MockBean
    RecommentRepository recommentRepository;

    @Test
    void createPost() {
        applicationPostRepository = mock(ApplicationPostRepository.class);
        userRepository = mock(UserRepository.class);
        postRepository = mock(PostRepository.class);
        commentRepository = mock(CommentRepository.class);
        recommentRepository = mock(RecommentRepository.class);

        User user = User.fake();

        ApplicationPost applicationPost = ApplicationPost.fake();

        CreateApplicationPostService createApplicationPostService =
            new CreateApplicationPostService(
                applicationPostRepository,
                userRepository,
                postRepository,
                commentRepository,
                recommentRepository
            );

        given(userRepository.findById(user.id())).willReturn(Optional.of(user));

        createApplicationPostService.createApplicationPost(user.id(), "프로", applicationPost.reason());

        verify(applicationPostRepository).save(any());
    }
}

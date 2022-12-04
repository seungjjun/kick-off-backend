package com.junstudio.kickoff.services;

import com.junstudio.kickoff.exceptions.AlreadyAppliedUser;
import com.junstudio.kickoff.exceptions.AlreadyExistingBoardName;
import com.junstudio.kickoff.models.ApplicationPost;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.ApplicationPostRepository;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

    CreateApplicationPostService createApplicationPostService;

    User user;
    ApplicationPost applicationPost;

    @BeforeEach
    void setup() {
        user = User.fake();
        applicationPost = ApplicationPost.fake();

        applicationPostRepository = mock(ApplicationPostRepository.class);
        userRepository = mock(UserRepository.class);
        postRepository = mock(PostRepository.class);
        commentRepository = mock(CommentRepository.class);
        recommentRepository = mock(RecommentRepository.class);

         createApplicationPostService =
            new CreateApplicationPostService(
                applicationPostRepository,
                userRepository,
                postRepository,
                commentRepository,
                recommentRepository
            );
    }

    @Test
    void createPost() {
        given(userRepository.findById(user.id())).willReturn(Optional.of(user));

        createApplicationPostService.createApplicationPost(user.id(), "프로", applicationPost.reason());

        verify(applicationPostRepository).save(any());
    }

    @Test
    void alreadyApplicationState() {
        given(userRepository.findById(user.id())).willReturn(Optional.of(user));

        given(applicationPostRepository.findAllByApplicant_Name(user.name()))
            .willReturn(List.of(applicationPost));

        assertThrows(AlreadyAppliedUser.class, () -> {
            createApplicationPostService.createApplicationPost(user.id(), "프로", applicationPost.reason());
        });
    }
}

package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.dtos.SearchedUserDto;
import com.junstudio.kickoff.dtos.UsersDto;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.models.UserId;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.LikeRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetUserAdminServiceTest {
    private UserRepository userRepository;
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private RecommentRepository recommentRepository;

    GetUserAdminService getUserAdminService;

    User user;
    Post post;
    Comment comment;
    Recomment recomment;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        postRepository = mock(PostRepository.class);
        commentRepository = mock(CommentRepository.class);
        recommentRepository = mock(RecommentRepository.class);

        getUserAdminService =
            new GetUserAdminService(
                userRepository,
                postRepository,
                commentRepository,
                recommentRepository
            );

        user = User.fake();
        post = Post.fake();
        comment = Comment.fake();
        recomment = Recomment.fake();
    }

    @Test
    void users() {
        given(userRepository.findAll()).willReturn(List.of(user));
        given(postRepository.findAllByUserId(any(UserId.class))).willReturn(List.of(post));
        given(commentRepository.findAllByUserId(any())).willReturn(List.of(comment));
        given(recommentRepository.findAllByUserId(any())).willReturn(List.of(recomment));

        UsersDto foundUsers = getUserAdminService.users();

        assertThat(foundUsers.getMembers().getUsers().size()).isEqualTo(1);

        assertThat(foundUsers.getMembers().getUsers().get(0).getGrade()).isEqualTo("아마추어");
    }

    @Test
    void search() {
        given(userRepository.findByName(any())).willReturn(Optional.ofNullable(user));

        given(postRepository.findAllByUserId(any(UserId.class))).willReturn(List.of(post));
        given(commentRepository.findAllByUserId(any())).willReturn(List.of(comment));
        given(recommentRepository.findAllByUserId(any())).willReturn(List.of(recomment));

        SearchedUserDto searchedUser = getUserAdminService.search(user.name());


        assertThat(searchedUser.getUser().getName()).isEqualTo("Jun");
        assertThat(searchedUser.getPostNumber()).isEqualTo(1);
        assertThat(searchedUser.getCommentNumber()).isEqualTo(2);
    }
}

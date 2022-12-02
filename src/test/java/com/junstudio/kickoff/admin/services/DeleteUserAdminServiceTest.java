package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.models.UserId;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.LikeRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class DeleteUserAdminServiceTest {
    @Test
    void delete() {
        UserRepository userRepository = mock(UserRepository.class);
        LikeRepository likeRepository = mock(LikeRepository.class);
        RecommentRepository recommentRepository = mock(RecommentRepository.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        PostRepository postRepository = mock(PostRepository.class);

        DeleteUserAdminService deleteUserAdminService =
            new DeleteUserAdminService(
                userRepository,
                likeRepository,
                recommentRepository,
                commentRepository,
                postRepository
            );

        List<Long> usersId = new ArrayList<>();

        usersId.add(1L);

        Like like = Like.fake();
        given(likeRepository.existsByUserId(any())).willReturn(true);
        given(likeRepository.findAllByUserId(any())).willReturn(List.of(like));

        Post post = Post.fake();
        given(postRepository.existsByUserId(any(UserId.class))).willReturn(true);
        given(postRepository.findAllByUserId(any(UserId.class))).willReturn(List.of(post));

        Recomment recomment = Recomment.fake();
        given(recommentRepository.existsByUserId(any())).willReturn(true);
        given(recommentRepository.findAllByUserId(any())).willReturn(List.of(recomment));

        Comment comment = Comment.fake();
        given(commentRepository.existsByUserId(any())).willReturn(true);
        given(commentRepository.findAllByUserId(any())).willReturn(List.of(comment));

        deleteUserAdminService.delete(usersId);

        verify(likeRepository).deleteAll(List.of(like));
        verify(recommentRepository).deleteAll(List.of(recomment));
        verify(commentRepository).deleteAll(List.of(comment));
        verify(postRepository).deleteAll(List.of(post));
        verify(userRepository).deleteAllById(usersId);
    }
}

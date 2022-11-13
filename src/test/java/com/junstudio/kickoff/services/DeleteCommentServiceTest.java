package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteCommentServiceTest {
    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private RecommentRepository recommentRepository;

    @Test
    void deleteStateFalse() {
        Comment comment = Comment.fake();

        commentRepository = mock(CommentRepository.class);
        recommentRepository = mock(RecommentRepository.class);

        DeleteCommentService deleteCommentService =
            new DeleteCommentService(commentRepository, recommentRepository);

        given(commentRepository.getReferenceById(comment.id())).willReturn(comment);

        deleteCommentService.deleteComment(comment.id());

        verify(commentRepository).delete(comment);

        assertThat(comment.isDeleted()).isFalse();
    }

    @Test
    void deleteStateTrue() {
        Comment comment = Comment.fake();

        commentRepository = mock(CommentRepository.class);
        recommentRepository = mock(RecommentRepository.class);

        DeleteCommentService deleteCommentService =
            new DeleteCommentService(commentRepository, recommentRepository);

        given(commentRepository.getReferenceById(comment.id())).willReturn(comment);

        given(recommentRepository.existsByCommentId(any(Long.class))).willReturn(true);

        deleteCommentService.deleteComment(comment.id());

        assertThat(comment.isDeleted()).isTrue();
    }
}

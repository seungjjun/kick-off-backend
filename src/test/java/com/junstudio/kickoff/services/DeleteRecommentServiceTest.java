package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteRecommentServiceTest {
    @MockBean
    private RecommentRepository recommentRepository;

    @MockBean
    private CommentRepository commentRepository;

    DeleteRecommentService deleteRecommentService;

    Recomment recomment;
    Comment comment;

    @BeforeEach
    void setup() {
        recomment = Recomment.fake();
        comment = Comment.fake();

        recommentRepository = mock(RecommentRepository.class);
        commentRepository = mock(CommentRepository.class);

        deleteRecommentService =
            new DeleteRecommentService(recommentRepository, commentRepository);
    }

    @Test
    void deleteOnlyRecomment() {
        given(recommentRepository.getReferenceById(recomment.id())).willReturn(recomment);

        given(commentRepository.getReferenceById(recomment.commentId())).willReturn(comment);

        deleteRecommentService.delete(recomment.id());

        verify(recommentRepository).delete(recomment);
    }

    @Test
    void deleteRecommentWithComment() {
        given(recommentRepository.getReferenceById(recomment.id())).willReturn(recomment);

        comment.delete();

        given(commentRepository.getReferenceById(recomment.commentId())).willReturn(comment);

        deleteRecommentService.delete(recomment.id());

        verify(recommentRepository).delete(recomment);
        verify(commentRepository).delete(comment);
    }

    @Test
    void deleteRecomments() {
        given(recommentRepository.getReferenceById(recomment.id())).willReturn(recomment);

        given(commentRepository.getReferenceById(recomment.commentId())).willReturn(comment);

        deleteRecommentService.deleteRecomments(List.of(recomment.id()));

        verify(recommentRepository).delete(recomment);
    }
}

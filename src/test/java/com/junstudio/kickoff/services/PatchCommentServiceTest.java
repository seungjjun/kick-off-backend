package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.repositories.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class PatchCommentServiceTest {
    @MockBean
    private CommentRepository commentRepository;

    @Test
    void patch() {
        commentRepository = mock(CommentRepository.class);

        PatchCommentService patchCommentService = new PatchCommentService(commentRepository);

        Comment comment = spy(Comment.fake());

        given(commentRepository.getReferenceById(any(Long.class))).willReturn(comment);

        patchCommentService.patchComment(comment.id(), comment.content());

        verify(comment).patch(comment.content());
    }
}

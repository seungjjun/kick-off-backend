package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateCommentServiceTest {
    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private CreateCommentService createCommentService;

    @BeforeEach
    void setup() {
        commentRepository = mock(CommentRepository.class);

        createCommentService = new CreateCommentService(commentRepository);
    }

    @Test
    void createComment() {
        Comment comment = mock(Comment.class);

        createCommentService.createComment(comment.content(), comment.userId(), comment.postId());

        verify(commentRepository).save(any(Comment.class));
    }

}
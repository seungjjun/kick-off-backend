package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCommentServiceTest {
    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private GetCommentService getCommentService;

    @BeforeEach
    void setup() {
        commentRepository = mock(CommentRepository.class);
        postRepository = mock(PostRepository.class);

        getCommentService = new GetCommentService(commentRepository, postRepository);
    }

    @Test
    void comments() {
        Comment comment = mock(Comment.class);

        given(commentRepository.findAll()).willReturn(List.of(comment));

        List<Comment> comments = getCommentService.comments();

        assertThat(comments).hasSize(1);
    }

    @Test
    void findComment() {
        Comment comment = new Comment(1L, "댓긍릐 댓글", 1L, 1L, LocalDateTime.now());

        given(commentRepository.findAllByPostId(any(Long.class)))
            .willReturn(List.of(comment));

        List<Comment> comments = getCommentService.findComment(1L);

        assertThat(comments).hasSize(1);
    }
}

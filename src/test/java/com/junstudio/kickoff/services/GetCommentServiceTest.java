package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.CommentsDto;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Content;
import com.junstudio.kickoff.models.PostId;
import com.junstudio.kickoff.models.UserId;
import com.junstudio.kickoff.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCommentServiceTest {
    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private GetCommentService getCommentService;

    @SpyBean
    private Pageable pageable;

    @BeforeEach
    void setup() {
        commentRepository = mock(CommentRepository.class);

        getCommentService = new GetCommentService(commentRepository);
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
        Comment comment = new Comment(1L, new Content("댓글의 댓글"), new UserId(1L), new PostId(1L), LocalDateTime.now());

        pageable = PageRequest.of(1, 10);

        List<Comment> comments = new ArrayList<>();
        comments.add(comment);

        Page<Comment> page = new PageImpl<>(comments);

        given(commentRepository.findAllByPostId_Value(comment.postId().value(), pageable))
            .willReturn(page);

        CommentsDto commentsDto = getCommentService.findComment(comment.postId().value(), pageable);

        assertThat(commentsDto.getComments()).hasSize(1);
    }
}

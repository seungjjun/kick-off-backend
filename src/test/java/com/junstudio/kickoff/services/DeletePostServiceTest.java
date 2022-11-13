package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.LikeRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeletePostServiceTest {
    @MockBean
    private PostRepository postRepository;

    @MockBean
    private LikeRepository likeRepository;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private RecommentRepository recommentRepository;

    private Post post;
    private Like like;
    private Comment comment;
    private Recomment recomment;

    @BeforeEach
    void setup() {
        post = Post.fake();
        like = Like.fake();
        comment = Comment.fake();
        recomment = Recomment.fake();

        postRepository = mock(PostRepository.class);
        likeRepository = mock(LikeRepository.class);
        commentRepository = mock(CommentRepository.class);
        recommentRepository = mock(RecommentRepository.class);
    }

    @Test
    void delete() {
        DeletePostService deletePostService
            = new DeletePostService(postRepository, likeRepository, commentRepository, recommentRepository);

        given(postRepository.getReferenceById(post.id())).willReturn(post);
        given(likeRepository.getReferenceByPostId(post.id())).willReturn(like);
        given(commentRepository.getReferenceByPostId(post.id())).willReturn(comment);
        given(recommentRepository.getReferenceByPostId(post.id())).willReturn(recomment);

        deletePostService.delete(post.id());

        verify(postRepository).delete(post);
    }
}

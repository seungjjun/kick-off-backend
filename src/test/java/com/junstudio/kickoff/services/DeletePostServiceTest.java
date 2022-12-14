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

import java.util.ArrayList;
import java.util.List;

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

    private DeletePostService deletePostService;

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

        deletePostService = new DeletePostService(
            postRepository,
            likeRepository,
            commentRepository,
            recommentRepository
        );
    }

    @Test
    void delete() {
        given(postRepository.getReferenceById(post.id())).willReturn(post);
        given(likeRepository.getReferenceByPostId_Value(post.id())).willReturn(like);
        given(commentRepository.getReferenceByPostId_Value(post.id())).willReturn(comment);
        given(recommentRepository.getReferenceByPostId_Value(post.id())).willReturn(recomment);

        deletePostService.delete(post.id());

        verify(postRepository).delete(post);
    }

    @Test
    void deletePosts() {
        List<Long> postsId = new ArrayList<>();

        postsId.add(1L);
        postsId.add(2L);

        deletePostService.deletePosts(postsId);

        verify(postRepository).deleteById(postsId.get(0));
        verify(postRepository).deleteById(postsId.get(1));
    }
}

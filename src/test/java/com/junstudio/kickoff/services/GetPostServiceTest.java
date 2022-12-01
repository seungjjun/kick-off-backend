package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.PostDetailDto;
import com.junstudio.kickoff.dtos.PostsDto;
import com.junstudio.kickoff.models.Board;
import com.junstudio.kickoff.models.Grade;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.PostInformation;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.models.UserId;
import com.junstudio.kickoff.repositories.BoardRepository;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.LikeRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetPostServiceTest {
    PostRepository postRepository;
    CommentRepository commentRepository;
    RecommentRepository recommentRepository;
    LikeRepository likeRepository;
    UserRepository userRepository;
    BoardRepository boardRepository;

    GetPostService getPostService;

    @BeforeEach
    void setup() {
        postRepository = mock(PostRepository.class);
        commentRepository = mock(CommentRepository.class);
        recommentRepository = mock(RecommentRepository.class);
        likeRepository = mock(LikeRepository.class);
        userRepository = mock(UserRepository.class);
        boardRepository = mock(BoardRepository.class);

        getPostService = new GetPostService(
            postRepository,
            commentRepository,
            recommentRepository,
            likeRepository,
            userRepository,
            boardRepository);
    }

    @Test
    void posts() {
        Post post = Post.fake();

        List<Post> posts = new ArrayList<>();
        posts.add(post);

        Page<Post> page = new PageImpl<>(posts);

        given(postRepository.findAll(Pageable.ofSize(1))).willReturn(page);

        PostsDto postsDto = getPostService.posts(post.getBoardId(), Pageable.ofSize(1));

        assertThat(postsDto).isNotNull();
    }

    @Test
    void findPost() {
        Post post = new Post(1L, new UserId(1L), 1L,
            new PostInformation("EPL start", "손흥민 아시아인 최초 EPL 득점왕"),
            3L, 1L, "imageUrl", LocalDateTime.now());

        Board board = Board.fake();
        User user = new User(1L, "jel1y", "encodedPassword",
            "Jun", "profileImage", new Grade("아마추어"), false);

        given(postRepository.findById(any())).willReturn(Optional.of(post));
        given(boardRepository.findById(any())).willReturn(Optional.of(board));
        given(userRepository.findById(any())).willReturn(Optional.of(user));

        PostDetailDto foundPost = getPostService.findPost(post.id());

        assertThat(foundPost.getPostInformation().getTitle()).isEqualTo("EPL start");

        verify(postRepository).findById(any());
    }

    @Test
    void searchWithTitle() {
        Post post = new Post(1L, new UserId(1L), 2L,
            new PostInformation("Laliga start", "이강인 개막전 득점"),
            3L, 1L, "imageUrl", LocalDateTime.now());

        List<Post> posts = new ArrayList<>();
        posts.add(post);

        Page<Post> page = new PageImpl<>(posts);

        given(postRepository.findByPostInformation_TitleContaining(
            "Laliga", Pageable.ofSize(1))
        ).willReturn(page);

        given(postRepository.findByBoardIdAndPostInformation_TitleContaining(
            2L, "Laliga", Pageable.ofSize(1))
        ).willReturn(page);

        PostsDto searchedPosts =
            getPostService.search(2L, "Laliga", "title", Pageable.ofSize(1));

        assertThat(searchedPosts.getPosts().getPosts().get(0)
            .getPostInformation().getTitle())
            .isEqualTo("Laliga start");

        verify(postRepository).findByBoardIdAndPostInformation_TitleContaining(any(), any(), any());
    }

    @Test
    void searchWithContent() {
        Post post = new Post(1L, new UserId(1L), 1L,
            new PostInformation("Laliga start", "이강인 개막전 득점"),
            3L, 1L, "imageUrl", LocalDateTime.now());

        List<Post> posts = new ArrayList<>();
        posts.add(post);

        Page<Post> page = new PageImpl<>(posts);

        given(postRepository.findByPostInformation_TitleContaining(
            "이강인", Pageable.ofSize(1))
        ).willReturn(page);

        given(postRepository.findByPostInformation_ContentContaining(
            "이강인", Pageable.ofSize(1))
        ).willReturn(page);

        given(postRepository.findByPostInformation_ContentContaining(
            "이강인", Pageable.ofSize(1))
        ).willReturn(page);

        PostsDto searchedPosts =
            getPostService.search(1L, "이강인", "content", Pageable.ofSize(1));

        assertThat(searchedPosts.getPosts().getPosts().get(0)
            .getPostInformation().getContent())
            .isEqualTo("이강인 개막전 득점");

        verify(postRepository).findByPostInformation_ContentContaining(any(), any());
    }
}

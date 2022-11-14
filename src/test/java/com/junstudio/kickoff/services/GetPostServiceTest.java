package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.CreatePostsDto;
import com.junstudio.kickoff.dtos.PostDetailDto;
import com.junstudio.kickoff.dtos.PostDto;
import com.junstudio.kickoff.dtos.PostsDto;
import com.junstudio.kickoff.models.Category;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.PostInformation;
import com.junstudio.kickoff.models.UserId;
import com.junstudio.kickoff.repositories.CategoryRepository;
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
    CategoryRepository categoryRepository;
    CommentRepository commentRepository;
    RecommentRepository recommentRepository;
    LikeRepository likeRepository;
    UserRepository userRepository;

    GetPostService getPostService;

    @BeforeEach
    void setup() {
        postRepository = mock(PostRepository.class);
        categoryRepository = mock(CategoryRepository.class);
        commentRepository = mock(CommentRepository.class);
        recommentRepository = mock(RecommentRepository.class);
        likeRepository = mock(LikeRepository.class);
        userRepository = mock(UserRepository.class);

        getPostService = new GetPostService(postRepository,
            categoryRepository,
            commentRepository,
            recommentRepository,
            likeRepository,
            userRepository);
    }

    @Test
    void posts() {
        Post post = Post.fake();

        List<Post> posts = new ArrayList<>();
        posts.add(post);

        Page<Post> page = new PageImpl<>(posts);

        given(postRepository.findAll(Pageable.ofSize(1))).willReturn(page);

        CreatePostsDto createPostsDto = getPostService.posts(Pageable.ofSize(1));

        assertThat(createPostsDto).isNotNull();
    }

    @Test
    void findPost() {
        Post post = new Post(1L, new UserId(1L), 1L,
            new PostInformation("EPL start", "손흥민 아시아인 최초 EPL 득점왕"),
            3L, 1L, "imageUrl", LocalDateTime.now());

        given(postRepository.findById(any())).willReturn(Optional.of(post));
        given(categoryRepository.findById(any())).willReturn(Optional.of(new Category()));

        PostDetailDto foundPost = getPostService.findPost(post.id());

        assertThat(foundPost.getPostInformation().getTitle()).isEqualTo("EPL start");

        verify(postRepository).findById(any());
    }

    @Test
    void findCategoryPosts() {
        Post post = Post.fake();

        List<Post> posts = new ArrayList<>();
        posts.add(post);

        Page<Post> page = new PageImpl<>(posts);

        given(postRepository.findAllByCategoryId(any(), any())).willReturn(page);

        PostsDto postsDto = getPostService.findCategoryPosts(any(), any(Pageable.class));

        assertThat(postsDto).isNotNull();
    }
}

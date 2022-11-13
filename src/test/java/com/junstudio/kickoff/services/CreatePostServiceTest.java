package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Category;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.CategoryRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreatePostServiceTest {
    PostRepository postRepository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;

    CreatePostService createPostService;

    @BeforeEach
    void setup() {
        postRepository = mock(PostRepository.class);
        userRepository = mock(UserRepository.class);
        categoryRepository = mock(CategoryRepository.class);

        createPostService = new CreatePostService(
            postRepository,
            userRepository,
            categoryRepository);
    }

    @Test
    void write() {
        User user = mock(User.class);
        given(userRepository.findById(user.id())).willReturn(Optional.of(user));

        Category category = mock(Category.class);
        given(categoryRepository.findById(category.id())).willReturn(Optional.of(category));

        Post post = Post.fake();

        createPostService.write(post.postInformation().getTitle(), post.postInformation().getContent(),
            post.imageUrl(), user.id(), category.id());

        verify(postRepository).save(any(Post.class));
    }
}

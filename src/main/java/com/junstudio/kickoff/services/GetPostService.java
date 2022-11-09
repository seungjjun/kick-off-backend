package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.PostDetailDto;
import com.junstudio.kickoff.dtos.PostDto;
import com.junstudio.kickoff.dtos.PostsDto;
import com.junstudio.kickoff.exceptions.CategoryNotFound;
import com.junstudio.kickoff.exceptions.PostNotFound;
import com.junstudio.kickoff.models.Category;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.repositories.CategoryRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetPostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public GetPostService(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    public PostsDto posts() {
//    Page<PostDto> post = postRepository.findAll(pageable);
        List<PostDto> posts = postRepository.findAll()
            .stream().map(Post::toDto)
            .collect(Collectors.toList());

        return new PostsDto(posts);
    }

    public PostDetailDto findPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFound::new);

        Category category = categoryRepository.findById(post.categoryId())
            .orElseThrow(CategoryNotFound::new);

        post.updateHit(post.hit());

        return post.toDetailDto(category);
    }

    public PostsDto findCategoryPosts(Long categoryId) {
        List<PostDto> categoryPosts = postRepository.findAllByCategoryId(categoryId)
            .stream().map(Post::toDto)
            .collect(Collectors.toList());
        return new PostsDto(categoryPosts);
    }
}


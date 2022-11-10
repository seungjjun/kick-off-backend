package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.PostPageDto;
import com.junstudio.kickoff.dtos.PostDetailDto;
import com.junstudio.kickoff.dtos.PostDto;
import com.junstudio.kickoff.dtos.PostsDto;
import com.junstudio.kickoff.exceptions.CategoryNotFound;
import com.junstudio.kickoff.exceptions.PostNotFound;
import com.junstudio.kickoff.models.Category;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.repositories.CategoryRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetPostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public GetPostService(PostRepository postRepository,
                          CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    public PostsDto posts(Pageable pageable) {
    List<PostDto> posts = postRepository.findAll(pageable)
        .stream().map(Post::toDto)
        .collect(Collectors.toList());

        return new PostsDto(posts,
            new PostPageDto(postRepository.findAll(pageable).getNumber() + 1,
                postRepository.findAll(pageable).getTotalElements()));
    }

    public PostDetailDto findPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFound::new);

        Category category = categoryRepository.findById(post.categoryId())
            .orElseThrow(CategoryNotFound::new);

        post.updateHit(post.hit());

        return post.toDetailDto(category);
    }

    public PostsDto findCategoryPosts(Long categoryId, Pageable pageable) {
        List<PostDto> categoryPosts =
            postRepository.findAllByCategoryId(categoryId, pageable)
            .stream().map(Post::toDto)
            .collect(Collectors.toList());

        System.out.println();
        return new PostsDto(categoryPosts,
            new PostPageDto(postRepository.findAllByCategoryId(categoryId, pageable).getNumber() + 1,
                postRepository.findAllByCategoryId(categoryId, pageable).getTotalElements()));
    }
}


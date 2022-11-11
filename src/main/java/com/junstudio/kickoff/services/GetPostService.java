package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.CategoryDto;
import com.junstudio.kickoff.dtos.CommentDto;
import com.junstudio.kickoff.dtos.CreatePostsDto;
import com.junstudio.kickoff.dtos.LikeDto;
import com.junstudio.kickoff.dtos.PostDetailDto;
import com.junstudio.kickoff.dtos.PostDto;
import com.junstudio.kickoff.dtos.PostPageDto;
import com.junstudio.kickoff.dtos.PostsDto;
import com.junstudio.kickoff.dtos.ReCommentDto;
import com.junstudio.kickoff.dtos.UserDto;
import com.junstudio.kickoff.exceptions.CategoryNotFound;
import com.junstudio.kickoff.exceptions.PostNotFound;
import com.junstudio.kickoff.models.Category;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.CategoryRepository;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.LikeRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import com.junstudio.kickoff.repositories.UserRepository;
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
    private final CommentRepository commentRepository;
    private final RecommentRepository recommentRepository;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    public GetPostService(PostRepository postRepository,
                          CategoryRepository categoryRepository,
                          CommentRepository commentRepository,
                          RecommentRepository recommentRepository,
                          LikeRepository likeRepository,
                          UserRepository userRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.commentRepository = commentRepository;
        this.recommentRepository = recommentRepository;
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
    }

    public CreatePostsDto posts(Pageable pageable) {
        List<PostDto> posts = getPostDtos(pageable);

        List<CommentDto> comments = getCommentDtos();

        List<ReCommentDto> reComments = getReCommentDtos();

        List<LikeDto> likes = getLikeDtos();

        List<CategoryDto> categories = getCategoryDtoList();

        List<UserDto> users = getUserDtos();

        PostsDto postsDto = new PostsDto(posts, comments, reComments, likes, categories, users);

        return new CreatePostsDto(postsDto,
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

        return new PostsDto(categoryPosts,
            new PostPageDto(postRepository.findAllByCategoryId(categoryId, pageable).getNumber() + 1,
                postRepository.findAllByCategoryId(categoryId, pageable).getTotalElements()));
    }

    private List<PostDto> getPostDtos(Pageable pageable) {
        return postRepository.findAll(pageable)
            .stream().map(Post::toDto)
            .collect(Collectors.toList());
    }

    private List<CommentDto> getCommentDtos() {
        return commentRepository.findAll()
            .stream().map(Comment::toDto)
            .collect(Collectors.toList());
    }

    private List<ReCommentDto> getReCommentDtos() {
        return recommentRepository.findAll()
            .stream().map(Recomment::toDto)
            .collect(Collectors.toList());
    }

    private List<LikeDto> getLikeDtos() {
        return likeRepository.findAll()
            .stream().map(Like::toDto)
            .collect(Collectors.toList());
    }

    private List<CategoryDto> getCategoryDtoList() {
        return categoryRepository.findAll()
            .stream().map(Category::toDto)
            .collect(Collectors.toList());
    }

    private List<UserDto> getUserDtos() {
        return userRepository.findAll()
            .stream().map(User::toDto)
            .collect(Collectors.toList());
    }
}


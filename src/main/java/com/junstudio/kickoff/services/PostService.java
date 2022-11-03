package com.junstudio.kickoff.services;

import com.junstudio.kickoff.exceptions.CategoryNotFound;
import com.junstudio.kickoff.exceptions.PostNotFound;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.Category;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.CategoryRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PostService {
  private final PostRepository postRepository;
  private final UserRepository userRepository;
  private final CategoryRepository categoryRepository;

  public PostService(PostRepository postRepository, UserRepository userRepository,
                     CategoryRepository categoryRepository) {
    this.postRepository = postRepository;
    this.userRepository = userRepository;
    this.categoryRepository = categoryRepository;
  }

  public Post findPost(Long postId) {
    Post post = postRepository.findById(postId).orElseThrow(PostNotFound::new);

    post.updateHit(post.hit());

    return post;
  }

  public User findUser(Long postId) {
    Post post = postRepository.findById(postId).orElseThrow(UserNotFound::new);

    return userRepository.findById(post.userId())
        .orElseThrow(UserNotFound::new);
  }

  public Category findCategory(Long postId) {
    Post post = postRepository.findById(postId).orElseThrow(CategoryNotFound::new);

    return categoryRepository.findById(post.categoryId())
        .orElseThrow(CategoryNotFound::new);
  }

  public List<Post> posts() {
    return postRepository.findAll();
  }

  public Post write(String title, String content,
                    String imageUrl, Long userId, Long categoryId) {
    User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);
    Category category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFound::new);

    Post post = new Post(title, content, 0L, imageUrl, user.id(), category.id());

    postRepository.save(post);
    return post;
  }
}


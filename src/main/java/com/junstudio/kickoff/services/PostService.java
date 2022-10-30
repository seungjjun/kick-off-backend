package com.junstudio.kickoff.services;

import com.junstudio.kickoff.exceptions.PostNotFound;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.repositories.PostRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PostService {
  private final PostRepository postRepository;

  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public Post findPost(Long postId) {
    Post post = postRepository.findById(postId).orElseThrow(PostNotFound::new);
    post.updateHit(post.getHit());
    return post;
  }

  public List<Post> posts() {
    return postRepository.findAll();
  }

  public Post write(String title, String content, String category, String imageUrl) {
    Post post = new Post(title, content, category, 0L, imageUrl);
    postRepository.save(post);
    return post;
  }
}


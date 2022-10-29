package com.junstudio.kickoff.services;

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

  public List<Post> posts() {
    return postRepository.findAll();
  }

  public void write(String title, String content, String category) {
    Post post = new Post(title, content, category);
    postRepository.save(post);
  }
}

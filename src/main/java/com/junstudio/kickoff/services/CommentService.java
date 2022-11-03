package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentService {
  private final CommentRepository commentRepository;
  private final PostRepository postRepository;

  public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
    this.commentRepository = commentRepository;
    this.postRepository = postRepository;
  }

  public List<Comment> comments() {
    return commentRepository.findAll();
  }

  public List<Comment> findComment(Long postId) {
    return commentRepository.findAllByPostId(postId);
  }

  public void createComment(String content, Long userId, Long postId) {
    Comment comment = new Comment(content, userId, postId);

    commentRepository.save(comment);
  }
}

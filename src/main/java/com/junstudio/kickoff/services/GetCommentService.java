package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.CommentDto;
import com.junstudio.kickoff.dtos.CommentPageDto;
import com.junstudio.kickoff.dtos.CommentsDto;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.repositories.CommentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetCommentService {
  private final CommentRepository commentRepository;

  public GetCommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public List<Comment> comments() {
    return commentRepository.findAll();
  }

  public CommentsDto findComment(Long postId, Pageable pageable) {
    List<CommentDto> comments = commentRepository.findAllByPostId_Value(postId, pageable)
        .stream().map(Comment::toDto)
        .collect(Collectors.toList());

    return new CommentsDto(comments,
        new CommentPageDto(commentRepository.findAllByPostId_Value(postId, pageable).getNumber() + 1,
        commentRepository.findAllByPostId_Value(postId, pageable).getTotalElements()));
  }
}

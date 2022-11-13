package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PatchCommentService {
    private final CommentRepository commentRepository;

    public PatchCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void patchComment(Long commentId, String content) {
        Comment comment = commentRepository.getReferenceById(commentId);

        comment.patch(content);
    }
}

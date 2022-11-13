package com.junstudio.kickoff.services;

import com.junstudio.kickoff.exceptions.RecommentNotFount;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DeleteCommentService {
    private final CommentRepository commentRepository;
    private final RecommentRepository recommentRepository;

    public DeleteCommentService(CommentRepository commentRepository,
                                RecommentRepository recommentRepository) {
        this.commentRepository = commentRepository;
        this.recommentRepository = recommentRepository;
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.getReferenceById(commentId);

        if(recommentRepository.existsByCommentId(commentId)) {
            comment.delete();
        }

        if (!recommentRepository.existsByCommentId(commentId)) {
            commentRepository.delete(comment);
        }
    }
}

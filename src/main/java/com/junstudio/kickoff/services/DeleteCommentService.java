package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

        checkExistedRecomment(commentId, comment);

        if (!recommentRepository.existsByCommentId(commentId)) {
            commentRepository.delete(comment);
        }
    }

    public void deleteComments(List<Long> commentId) {
        commentId.forEach(this::deleteComment);
    }

    private void checkExistedRecomment(Long commentId, Comment comment) {
        if(recommentRepository.existsByCommentId(commentId)) {
            comment.delete();
        }
    }
}

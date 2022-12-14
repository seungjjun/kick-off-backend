package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeleteRecommentService {
    private final RecommentRepository recommentRepository;
    private final CommentRepository commentRepository;

    public DeleteRecommentService(RecommentRepository recommentRepository,
                                  CommentRepository commentRepository) {
        this.recommentRepository = recommentRepository;
        this.commentRepository = commentRepository;
    }

    public void delete(Long recommentId) {
        Recomment recomment = recommentRepository.getReferenceById(recommentId);

        if(findComment(recomment).isDeleted()) {
            Comment comment = findComment(recomment);

            recommentRepository.delete(recomment);

            if(!recommentRepository.existsByCommentId(comment.id())) {
                commentRepository.delete(comment);
            }
        }

        if(!findComment(recomment).isDeleted()) {
            recommentRepository.delete(recomment);
        }
    }

    public void deleteRecomments(List<Long> recommentId) {
        recommentId.forEach(this::delete);
    }

    private Comment findComment(Recomment recomment) {
        return commentRepository.getReferenceById(
            recomment.commentId());
    }
}

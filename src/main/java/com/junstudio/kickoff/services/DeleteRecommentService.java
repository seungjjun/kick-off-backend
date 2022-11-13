package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

        if(commentRepository.getReferenceById(recomment.getCommentId()).isDeleted()) {
            Comment comment = commentRepository.getReferenceById(recomment.getCommentId());

            recommentRepository.delete(recomment);

            if(!recommentRepository.existsByCommentId(comment.id())) {
                commentRepository.delete(comment);
            }
        }

        if(!commentRepository.getReferenceById(recomment.getCommentId()).isDeleted()) {
            recommentRepository.delete(recomment);
        }
    }
}

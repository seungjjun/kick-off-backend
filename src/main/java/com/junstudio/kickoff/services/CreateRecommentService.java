package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.repositories.RecommentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CreateRecommentService {
    private final RecommentRepository recommentRepository;

    public CreateRecommentService(RecommentRepository recommentRepository) {
        this.recommentRepository = recommentRepository;
    }

    public void createRecomment(String content, Long commentId, Long userId, Long postId) {
        Recomment recomment = new Recomment(content, commentId, userId, postId);

        recommentRepository.save(recomment);
    }
}

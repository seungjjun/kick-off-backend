package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Content;
import com.junstudio.kickoff.models.PostId;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.models.UserId;
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
        Recomment recomment = new Recomment(
            new Content(content),
            commentId,
            new UserId(userId),
            new PostId(postId)
        );

        recommentRepository.save(recomment);
    }
}

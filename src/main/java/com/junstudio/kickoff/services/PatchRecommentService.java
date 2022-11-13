package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.repositories.RecommentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PatchRecommentService {
    private final RecommentRepository recommentRepository;

    public PatchRecommentService(RecommentRepository recommentRepository) {
        this.recommentRepository = recommentRepository;
    }

    public void patch(Long recommentId, String content) {
        Recomment recomment = recommentRepository.getReferenceById(recommentId);

        recomment.patch(content);
    }
}

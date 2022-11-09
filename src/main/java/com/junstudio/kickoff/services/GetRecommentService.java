package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.repositories.RecommentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetRecommentService {
    private final RecommentRepository recommentRepository;

    public GetRecommentService(RecommentRepository recommentRepository) {
        this.recommentRepository = recommentRepository;
    }

    public List<Recomment> recomments() {
        return recommentRepository.findAll();
    }

    public List<Recomment> findRecomment(Long postId) {
        return recommentRepository.findAllByPostId(postId);
    }
}

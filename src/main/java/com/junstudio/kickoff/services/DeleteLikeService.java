package com.junstudio.kickoff.services;

import com.junstudio.kickoff.repositories.LikeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeleteLikeService {
    private final LikeRepository likeRepository;

    public DeleteLikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public void deleteLike(List<Long> likeId) {
        likeId.forEach(likeRepository::deleteByPostId_Value);
    }
}

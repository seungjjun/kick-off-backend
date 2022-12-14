package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.repositories.LikeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetLikeService {
    private final LikeRepository likeRepository;

    public GetLikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public List<Like> likes() {
        return likeRepository.findAll();
    }

    public List<Like> findLike(Long postId) {
        return likeRepository.findAllByPostId_Value(postId);
    }
}

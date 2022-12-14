package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.repositories.LikeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CreateLikeService {
    private final LikeRepository likeRepository;

    public CreateLikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public void countLike(Long postId, Long userId) {
        if (likeRepository.existsByPostId_Value(postId)) {
            List<Like> foundLikes = likeRepository.findAllByUserId_Value(userId);

            for (Like foundLike : foundLikes) {
                if (foundLike.userId().value().equals(userId)) {
                    likeRepository.deleteById(foundLike.id());
                    return;
                }
            }

            Like newLike = new Like(postId, userId);
            likeRepository.save(newLike);
            return;
        }

        Like newLike = new Like(postId, userId);
        likeRepository.save(newLike);
    }
}

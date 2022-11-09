package com.junstudio.kickoff.services;

import com.junstudio.kickoff.exceptions.PostNotFound;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.LikeRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
public class CreateLikeService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    public CreateLikeService(PostRepository postRepository,
                             UserRepository userRepository,
                             LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }

    public void countLike(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFound::new);
        User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);

        Like like = new Like(-1L, post.id(), user.id());
        Like foundLike = likeRepository.findByPostId(postId).orElse(like);

        if (!(foundLike.equals(like))) {
            if (Objects.equals(foundLike.userId(), userId)) {
                likeRepository.deleteById(foundLike.id());
                return;
            }
        }

        Like newLike = new Like(post.id(), user.id());
        likeRepository.save(newLike);
    }
}

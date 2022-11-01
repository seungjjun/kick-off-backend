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
public class LikeService {
  private final LikeRepository likeRepository;
  private final PostRepository postRepository;
  private final UserRepository userRepository;

  public LikeService(LikeRepository likeRepository,
                     PostRepository postRepository,
                     UserRepository userRepository) {
    this.likeRepository = likeRepository;
    this.postRepository = postRepository;
    this.userRepository = userRepository;
  }

  public void countLike(Long postId, Long userId) {
    Post post = postRepository.findById(postId).orElseThrow(PostNotFound::new);
    User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);

    Like like = new Like(-1L, post, user);
    Like foundLike = likeRepository.findByPostId(postId).orElse(like);

    if (!(foundLike.equals(like))) {
      if (Objects.equals(foundLike.getUser().getId(), userId)) {
        likeRepository.deleteById(foundLike.getId());
        return;
      }
    }

    Like newLike = new Like(post, user);
    likeRepository.save(newLike);
  }
}

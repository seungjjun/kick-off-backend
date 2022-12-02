package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.models.UserId;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.LikeRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeleteUserAdminService {
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final RecommentRepository recommentRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public DeleteUserAdminService(UserRepository userRepository,
                                  LikeRepository likeRepository,
                                  RecommentRepository recommentRepository,
                                  CommentRepository commentRepository,
                                  PostRepository postRepository) {
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.recommentRepository = recommentRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public void delete(List<Long> usersId) {
        usersId.forEach(this::deleteLikes);
        usersId.forEach(this::deleteRecomments);
        usersId.forEach(this::deleteComments);
        usersId.forEach(this::deletePosts);
        userRepository.deleteAllById(usersId);
    }

    private void deleteLikes(Long userId) {
        if(likeRepository.existsByUserId(userId)) {
            List<Like> likes = likeRepository.findAllByUserId(userId);

            likeRepository.deleteAll(likes);
        }
    }

    private void deleteRecomments(Long userId) {
        if(recommentRepository.existsByUserId(userId)) {
            List<Recomment> recomments = recommentRepository.findAllByUserId(userId);

            recommentRepository.deleteAll(recomments);
        }
    }

    private void deleteComments(Long userId) {
        if(commentRepository.existsByUserId(userId)) {
            List<Comment> comments = commentRepository.findAllByUserId(userId);

            commentRepository.deleteAll(comments);
        }
    }

    private void deletePosts(Long userId) {
        if(postRepository.existsByUserId(new UserId(userId))) {
            List<Post> posts = postRepository.findAllByUserId(new UserId(userId));

            postRepository.deleteAll(posts);
        }
    }
}

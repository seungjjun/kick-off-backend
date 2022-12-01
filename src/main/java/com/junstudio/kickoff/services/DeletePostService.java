package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.LikeRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeletePostService {
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final RecommentRepository recommentRepository;

    public DeletePostService(PostRepository postRepository,
                             LikeRepository likeRepository,
                             CommentRepository commentRepository,
                             RecommentRepository recommentRepository) {
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
        this.recommentRepository = recommentRepository;
    }

    public void delete(Long postId) {
        Post post = postRepository.getReferenceById(postId);

        deleteLikes(postId);
        deleteComments(postId);
        deleteRecomments(postId);

        postRepository.delete(post);
    }

    public void deletePosts(List<Long> selectedPosts) {
        selectedPosts.forEach(this::deleteLikes);
        selectedPosts.forEach(this::deleteComments);
        selectedPosts.forEach(this::deleteRecomments);
        selectedPosts.forEach(postRepository::deleteById);
    }

    private void deleteRecomments(Long postId) {
        if (recommentRepository.existsByPostId(postId)) {
            List<Recomment> recomments = recommentRepository.findAllByPostId(postId);

            for (int i = 0; i < recomments.size(); i += 1) {
                recommentRepository.deleteAllById(recomments.get(i).id());
            }
        }
    }

    private void deleteComments(Long postId) {
        if(commentRepository.existsByPostId(postId)) {
            List<Comment> comments = commentRepository.findAllByPostId(postId);

            for (int i = 0; i < comments.size(); i += 1) {
                commentRepository.deleteAllById(comments.get(i).id());
            }
        }
    }

    private void deleteLikes(Long postId) {
        if(likeRepository.existsByPostId(postId)) {
            List<Like> likes = likeRepository.findAllByPostId(postId);

            for (int i = 0; i < likes.size(); i += 1) {
                likeRepository.deleteAllById(likes.get(i).id());
            }
        }
    }
}

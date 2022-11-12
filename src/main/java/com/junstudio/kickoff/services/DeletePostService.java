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
        Like like = likeRepository.getReferenceByPostId(postId);
        Comment comment = commentRepository.getReferenceByPostId(postId);
        Recomment recomment = recommentRepository.getReferenceByPostId(postId);

        likeRepository.deleteAllById(like.id());
        recommentRepository.deleteAllById(recomment.getId());
        commentRepository.deleteAllById(comment.id());
        postRepository.delete(post);
    }
}

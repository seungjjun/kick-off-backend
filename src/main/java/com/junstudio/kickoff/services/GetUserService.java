package com.junstudio.kickoff.services;

import com.amazonaws.services.kms.model.NotFoundException;
import com.junstudio.kickoff.dtos.CommentDto;
import com.junstudio.kickoff.dtos.PostDto;
import com.junstudio.kickoff.dtos.ReCommentDto;
import com.junstudio.kickoff.dtos.UsersDto;
import com.junstudio.kickoff.exceptions.PostNotFound;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.models.UserId;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.LikeRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetUserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final RecommentRepository recommentRepository;

    public GetUserService(UserRepository userRepository,
                          PostRepository postRepository,
                          CommentRepository commentRepository,
                          LikeRepository likeRepository,
                          RecommentRepository recommentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
        this.recommentRepository = recommentRepository;
    }

    public List<User> users() {
        return userRepository.findAll();
    }

    public User findMyInformation(String identification) {
        return userRepository.findByIdentification(identification)
            .orElseThrow(UserNotFound::new);
    }

    public UsersDto findUser(Long userId, String identification) {
        User user = userRepository.findById(userId)
            .orElseThrow(UserNotFound::new);

        if(user.identification().equals(identification)) {
            user.changeTokenState();
        }

        List<PostDto> posts = postRepository.findAllByUserId(new UserId(userId))
            .stream().map(Post::toDto).collect(Collectors.toList());

        List<CommentDto> comments = commentRepository.findAllByUserId(userId)
            .stream().map(Comment::toDto).collect(Collectors.toList());

        List<ReCommentDto> reCommentDtos = recommentRepository.findAllByUserId(userId)
            .stream().map(Recomment::toDto).collect(Collectors.toList());

        List<Like> likes = likeRepository.findAllByUserId(userId);

        List<PostDto> likedPosts = new ArrayList<>();

        likes.forEach(like -> {
            likedPosts.add(postRepository.findById(like.postId())
                .orElseThrow(PostNotFound::new)
                .toDto());
        });

        return new UsersDto(user, posts, comments, reCommentDtos, likedPosts);
    }
}

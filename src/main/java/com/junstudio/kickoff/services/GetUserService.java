package com.junstudio.kickoff.services;

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

    public UsersDto findMyInformation(String identification) {
        User user = userRepository.findByIdentification(identification)
            .orElseThrow(UserNotFound::new);

        user.changeTokenState();

        List<PostDto> posts = posts(user);

        List<CommentDto> comments = comments(user);

        List<ReCommentDto> reCommentDtos = recomments(user);

        List<Like> likes = likes(user);

        List<PostDto> likedPosts = new ArrayList<>();

        addLikes(likes, likedPosts);

        return new UsersDto(user, posts, comments, reCommentDtos, likedPosts);
    }

    public UsersDto findUser(String userName, String identification) {
        User user = userRepository.findByName(userName)
            .orElseThrow(UserNotFound::new);

        if (user.identification().equals(identification)) {
            user.changeTokenState();
        }

        if (!user.identification().equals(identification)) {
            user.setTokenState();
        }

        List<PostDto> posts = posts(user);

        List<CommentDto> comments = comments(user);

        List<ReCommentDto> reCommentDtos = recomments(user);

        List<Like> likes = likes(user);

        List<PostDto> likedPosts = new ArrayList<>();

        addLikes(likes, likedPosts);

        return new UsersDto(user, posts, comments, reCommentDtos, likedPosts);
    }

    private List<PostDto> posts(User user) {
        return postRepository.findAllByUserId_Value(user.id())
            .stream().map(Post::toDto).collect(Collectors.toList());
    }

    private List<CommentDto> comments(User user) {
        return commentRepository.findAllByUserId_Value(user.id())
            .stream().map(Comment::toDto).collect(Collectors.toList());
    }

    private List<ReCommentDto> recomments(User user) {
        return recommentRepository.findAllByUserId_Value(user.id())
            .stream().map(Recomment::toDto).collect(Collectors.toList());
    }

    private List<Like> likes(User user) {
        return likeRepository.findAllByUserId_Value(user.id());
    }

    private void addLikes(List<Like> likes, List<PostDto> likedPosts) {
        likes.forEach(like -> {
            likedPosts.add(postRepository.findById(like.postId().value())
                .orElseThrow(PostNotFound::new)
                .toDto());
        });
    }
}

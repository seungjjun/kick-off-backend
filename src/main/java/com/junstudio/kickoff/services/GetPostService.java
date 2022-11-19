package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.BoardDto;
import com.junstudio.kickoff.dtos.CommentDto;
import com.junstudio.kickoff.dtos.CreatePostsDto;
import com.junstudio.kickoff.dtos.LikeDto;
import com.junstudio.kickoff.dtos.PostDetailDto;
import com.junstudio.kickoff.dtos.PostDto;
import com.junstudio.kickoff.dtos.PostPageDto;
import com.junstudio.kickoff.dtos.PostsDto;
import com.junstudio.kickoff.dtos.ReCommentDto;
import com.junstudio.kickoff.dtos.UserDto;
import com.junstudio.kickoff.exceptions.BoardNotFound;
import com.junstudio.kickoff.exceptions.PostNotFound;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.Board;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.BoardRepository;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.LikeRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetPostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final RecommentRepository recommentRepository;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public GetPostService(PostRepository postRepository,
                          CommentRepository commentRepository,
                          RecommentRepository recommentRepository,
                          LikeRepository likeRepository,
                          UserRepository userRepository,
                          BoardRepository boardRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.recommentRepository = recommentRepository;
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }

    public PostsDto posts(Long boardId, Pageable pageable) {
        List<CommentDto> comments = commentDto();

        List<ReCommentDto> reComments = recommentDto();

        List<LikeDto> likes = likeDto();

        List<UserDto> users = userDto();

        List<BoardDto> boards = boardDto();

        if (boardId != 1) {
            List<PostDto> posts = postRepository.findAllByBoardId(boardId, pageable)
                .stream().map(Post::toDto).collect(Collectors.toList());

            CreatePostsDto createPostsDto =
                new CreatePostsDto(posts, comments, reComments, likes, users, boards);

            return new PostsDto(createPostsDto,
                new PostPageDto(postRepository.findAll(pageable).getNumber() + 1,
                    postRepository.findAll(pageable).getTotalElements()));
        }

        List<PostDto> posts = postDto(pageable);

        CreatePostsDto createPostsDto =
            new CreatePostsDto(posts, comments, reComments, likes, users, boards);

        return new PostsDto(createPostsDto,
            new PostPageDto(postRepository.findAll(pageable).getNumber() + 1,
                postRepository.findAll(pageable).getTotalElements()));
    }

    public PostDetailDto findPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFound::new);

        Board board = boardRepository.findById(post.getBoardId())
            .orElseThrow(BoardNotFound::new);

        User user = userRepository.findById(post.userId().getUserId())
            .orElseThrow(UserNotFound::new);

        post.updateHit(post.hit());

        return post.toDetailDto(board, user);
    }

    private List<PostDto> postDto(Pageable pageable) {
        return postRepository.findAll(pageable)
            .stream().map(Post::toDto)
            .collect(Collectors.toList());
    }

    private List<CommentDto> commentDto() {
        return commentRepository.findAll()
            .stream().map(Comment::toDto)
            .collect(Collectors.toList());
    }

    private List<ReCommentDto> recommentDto() {
        return recommentRepository.findAll()
            .stream().map(Recomment::toDto)
            .collect(Collectors.toList());
    }

    private List<LikeDto> likeDto() {
        return likeRepository.findAll()
            .stream().map(Like::toDto)
            .collect(Collectors.toList());
    }

    private List<UserDto> userDto() {
        return userRepository.findAll()
            .stream().map(User::toDto)
            .collect(Collectors.toList());
    }

    private List<BoardDto> boardDto() {
        return boardRepository.findAll()
            .stream().map(Board::toDto)
            .collect(Collectors.toList());
    }
}


package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Board;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.BoardRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreatePostServiceTest {
    PostRepository postRepository;
    UserRepository userRepository;
    BoardRepository boardRepository;

    CreatePostService createPostService;

    @BeforeEach
    void setup() {
        postRepository = mock(PostRepository.class);
        userRepository = mock(UserRepository.class);
        boardRepository = mock(BoardRepository.class);

        createPostService = new CreatePostService(
            postRepository,
            userRepository,
            boardRepository);
    }

    @Test
    void write() {
        User user = mock(User.class);
        given(userRepository.findById(user.id())).willReturn(Optional.of(user));

        Board board = mock(Board.class);
        given(boardRepository.findById(board.id())).willReturn(Optional.of(board));

        Post post = Post.fake();

        createPostService.write(post.postInformation().getTitle(), post.postInformation().getContent(),
            post.imageUrl(), user.id(), board.id());

        verify(postRepository).save(any(Post.class));
    }
}
